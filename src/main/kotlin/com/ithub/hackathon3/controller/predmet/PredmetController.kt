package com.ithub.hackathon3.controller.predmet

import com.ithub.hackathon3.model.Predmet
import com.ithub.hackathon3.service.PredmetService
import com.ithub.hackathon3.service.TeacherService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class PredmetController(
    val predmetService: PredmetService,
    val teacherService: TeacherService
) {

    suspend fun findAllPredmets(request: ServerRequest): ServerResponse {
        val predmets = predmetService.findAllPredmets()
        return ServerResponse
            .ok()
            .bodyValueAndAwait(predmets)
    }

    suspend fun findPredmetById(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toInt()
        val predmet = predmetService.findPredmetById(id)

        return ServerResponse
            .ok()
            .bodyValueAndAwait(predmet)
    }

    suspend fun findTeachersByPredmet(request: ServerRequest): ServerResponse {
        val predmetId = request.pathVariable("id").toInt()
        val predmet = predmetService.findPredmetById(predmetId)

        return ServerResponse
            .ok()
            .bodyValueAndAwait(predmet.teachers)
    }

    suspend fun createPredmet(request: ServerRequest): ServerResponse {
        val body = request.awaitBody(CreatePredmetBody::class)
        return try {
            val predmet = predmetService.insertPredmet(body.toPredmet())
            ServerResponse
                .ok()
                .bodyValueAndAwait(predmet)

        } catch (e: Exception) {
            ServerResponse
                .badRequest()
                .bodyValueAndAwait(e.localizedMessage)
        }
    }

    suspend fun updatePredmet(request: ServerRequest): ServerResponse {
        val body = request.awaitBody(Predmet::class)
        return try {
            val updatedPredmet = predmetService.updatePredmet(body)
            ServerResponse
                .ok()
                .bodyValueAndAwait(updatedPredmet)

        } catch (e: Exception) {
            ServerResponse
                .badRequest()
                .bodyValueAndAwait(e.localizedMessage)
        }
    }

    suspend fun deletePredmet(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toInt()

        return try {
            val deletedId = predmetService.deletePredmetById(id)
            ServerResponse
                .ok()
                .bodyValueAndAwait(deletedId)

        } catch (e: Exception) {
            ServerResponse
                .badRequest()
                .bodyValueAndAwait(e.localizedMessage)
        }
    }

    suspend fun addTeachersToPredmet(request: ServerRequest): ServerResponse {
        val teacherIds = request.awaitBody(AddTeachersToPredmet::class).teacherIds
        val predmetId = request.pathVariable("id").toInt()
        val predmet = predmetService.findPredmetById(predmetId)
        val teachers = teacherService.findAllTeachersById(teacherIds).toHashSet()
        return ServerResponse
            .ok()
            .bodyValueAndAwait(predmetService.addTeachersToPredmet(predmet, teachers))
    }
}
