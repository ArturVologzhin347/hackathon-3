package com.ithub.hackathon3.service

import com.ithub.hackathon3.model.Teacher

interface TeacherService {

    fun findAllTeachers(): List<Teacher>

    fun findTeacherById(id: String): Teacher

    fun findAllTeachersById(ids: Iterable<String>): List<Teacher>

    fun exists(id: String): Boolean

}
