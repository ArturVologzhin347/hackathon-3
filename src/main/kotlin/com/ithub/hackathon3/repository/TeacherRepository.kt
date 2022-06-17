package com.ithub.hackathon3.repository

import com.ithub.hackathon3.model.Teacher
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherRepository : JpaRepository<Teacher, String>
