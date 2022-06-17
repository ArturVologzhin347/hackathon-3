package com.ithub.hackathon3.controller

import com.ithub.hackathon3.service.TeacherService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class TeacherController(
   val teacherService: TeacherService
) {

    suspend fun findAllTeachers(request: ServerRequest): ServerResponse {
        val teachers = teacherService.findAllTeachers()
        return ServerResponse.ok().bodyValueAndAwait(teachers)
    }

    suspend fun findTeacherById(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        val teacher = teacherService.findTeacherById(id)

        return ServerResponse
            .ok()
            .bodyValueAndAwait(teacher)
    }

    suspend fun findPredmetsByTeacher(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        val teacher = teacherService.findTeacherById(id)
        return ServerResponse
            .ok()
            .bodyValueAndAwait(teacher.predmets)
    }
}
