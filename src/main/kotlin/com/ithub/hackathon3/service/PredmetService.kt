package com.ithub.hackathon3.service

import com.ithub.hackathon3.model.Predmet
import com.ithub.hackathon3.model.Teacher

interface PredmetService {

    fun findAllPredmets(): List<Predmet>

    fun findPredmetById(id: Int): Predmet

    fun findTeachersByPredmet(predmet: Predmet): Set<Teacher>

    fun insertPredmet(predmet: Predmet): Predmet

    fun updatePredmet(predmet: Predmet): Predmet

    fun deletePredmetById(id: Int): Int

    fun exists(id: Int): Boolean

    fun addTeachersToPredmet(predmet: Predmet, teachers: Set<Teacher>): Set<Teacher>

    companion object {
        const val TITLE_MAX_LENGTH = 30
        const val SHORT_T_MAX_LENGTH = 5
    }

}
