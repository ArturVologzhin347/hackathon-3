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
        val trimmedPredmet = predmet.copy(
            id = -1,
            title = predmet.title.trim(),
            shortT = predmet.shortT.trim()
        )

        checkIsValid(trimmedPredmet)
        return predmetRepository.save(trimmedPredmet)
    }

    override fun updatePredmet(predmet: Predmet): Predmet {
        checkIsExists(predmet)
        checkIsValid(predmet)

        val oldPredmet = findPredmetById(predmet.id)
        predmet.teachers = oldPredmet.teachers

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

    private fun checkIsValid(predmet: Predmet) {
        with(predmet) {
            check(title.length <= PredmetService.TITLE_MAX_LENGTH) {
            "Title length is varchar(${PredmetService.TITLE_MAX_LENGTH})"
        }

            check(shortT.length <= PredmetService.SHORT_T_MAX_LENGTH) {
                "ShortT length is varchar(${PredmetService.SHORT_T_MAX_LENGTH})"
            }
        }
    }

    private fun checkIsExists(predmet: Predmet) {
        checkIsExists(predmet.id)
    }

    private fun checkIsExists(predmetId: Int) {
        check(exists(predmetId)) { "Predmet with id: $predmetId does not exists." }
    }

}
