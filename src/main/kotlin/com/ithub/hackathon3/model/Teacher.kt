package com.ithub.hackathon3.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity

@Table(
    name = "teacher", indexes = [
        Index(name = "TeacherID", columnList = "id", unique = true)
    ]
)
data class Teacher(
    @Id @Column(name = "id", nullable = false, length = 4)
    val id: String
) {

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "teachers")
    val predmets: MutableSet<Predmet> = mutableSetOf()
}
