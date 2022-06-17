package com.ithub.hackathon3.service.impl

import com.ithub.hackathon3.model.Predmet
import com.ithub.hackathon3.model.Teacher
import com.ithub.hackathon3.repository.PredmetRepository
import com.ithub.hackathon3.service.PredmetService
import org.springframework.stereotype.Service

@Service
class PredmetServiceImpl(
    private val predmetRepository: PredmetRepository
) : PredmetService {

    override fun findAllPredmets(): List<Predmet> {
        return predmetRepository.findAll()
    }

    override fun findPredmetById(id: Int): Predmet {
        checkIsExists(id)
        return predmetRepository.findById(id).get()
    }

    override fun findTeachersByPredmet(predmet: Predmet): Set<Teacher> {
        return predmet.teachers
    }

    override fun insertPredmet(predmet: Predmet): Predmet {
        val trimmedTitle = predmet.title.trim()
        val trimmedShortT = predmet.shortT.trim()

        check(trimmedTitle.length <= PredmetService.TITLE_MAX_LENGTH) {
            "Title length is varchar(${PredmetService.TITLE_MAX_LENGTH})"
        }

        check(trimmedShortT.length <= PredmetService.SHORT_T_MAX_LENGTH) {
            "ShortT length is varchar(${PredmetService.SHORT_T_MAX_LENGTH})"
        }

        return predmetRepository.save(Predmet(-1, trimmedTitle, trimmedShortT))
    }

    override fun updatePredmet(predmet: Predmet): Predmet {
        checkIsExists(predmet)
        return predmetRepository.save(predmet)
    }

    override fun deletePredmetById(id: Int): Int {
        checkIsExists(id)
        predmetRepository.deleteById(id)
        return id
    }

    override fun exists(id: Int): Boolean {
        return predmetRepository.existsById(id)
    }

    override fun addTeachersToPredmet(predmet: Predmet, teachers: Set<Teacher>): Set<Teacher> {
        return predmetRepository.save(predmet.apply { this.teachers = teachers }).teachers
    }

    private fun checkIsExists(predmet: Predmet) {
        checkIsExists(predmet.id)
    }

    private fun checkIsExists(predmetId: Int) {
        check(exists(predmetId)) { "Predmet with id: $predmetId does not exists." }
    }

}
