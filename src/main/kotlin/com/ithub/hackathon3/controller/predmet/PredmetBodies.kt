package com.ithub.hackathon3.controller.predmet

import com.ithub.hackathon3.model.Predmet

data class CreatePredmetBody(
    val title: String,
    val shortT: String
) {

    fun toPredmet() = Predmet(
        id = -1,
        title = title,
        shortT = shortT
    )
}

data class AddTeachersToPredmet(
    val teacherIds: Set<String>
)
