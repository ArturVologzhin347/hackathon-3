package com.ithub.hackathon3.service.impl

import com.ithub.hackathon3.model.Teacher
import com.ithub.hackathon3.repository.TeacherRepository
import com.ithub.hackathon3.service.TeacherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TeacherServiceImpl(
    @Autowired private val teacherRepository: TeacherRepository
) : TeacherService {

    override fun findAllTeachers(): List<Teacher> {
        return teacherRepository.findAll()
    }

    override fun findTeacherById(id: String): Teacher {
        checkIsExists(id)
        return teacherRepository.findById(id).get()
    }

    override fun findAllTeachersById(ids: Iterable<String>): List<Teacher> {
        return teacherRepository.findAllById(ids)
    }

    override fun exists(id: String): Boolean {
        return teacherRepository.existsById(id)
    }

    private fun checkIsExists(teacherId: String) {
        check(exists(teacherId)) { "Predmet with id: $teacherId does not exists." }
    }

}
