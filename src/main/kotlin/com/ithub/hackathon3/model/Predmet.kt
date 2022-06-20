package com.ithub.hackathon3.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "predmet")
data class Predmet(
    @Id @GeneratedValue @Column(name = "id", nullable = false)
    val id: Int,
    @Column(name = "title", nullable = false, length = 30)
    val title: String,
    @Column(name = "shortT", nullable = false, length = 5)
    val shortT: String
) {

    @JsonIgnore
    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = [
            CascadeType.PERSIST, CascadeType.MERGE
        ]
    )
    @JoinTable(
        name = "teachpred",
        joinColumns = [JoinColumn(name = "prid")],
        inverseJoinColumns = [JoinColumn(name = "tid")]
    )
    var teachers: Set<Teacher> = hashSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Predmet

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id)"
    }
}
