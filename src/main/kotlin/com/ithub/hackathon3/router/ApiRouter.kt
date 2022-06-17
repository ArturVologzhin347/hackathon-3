package com.ithub.hackathon3.router

import com.ithub.hackathon3.controller.predmet.PredmetController
import com.ithub.hackathon3.controller.TeacherController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ApiRouter(
    private val teacherController: TeacherController,
    private val predmetController: PredmetController
) {

    @Bean
    fun route() = coRouter {
        "api/".nest {
            "teachers".nest {
                GET("", teacherController::findAllTeachers)

                "/{id}".nest {
                    GET("", teacherController::findTeacherById)
                    GET("/predmets", teacherController::findPredmetsByTeacher)
                }
            }

            "predmets".nest {
                GET("", predmetController::findAllPredmets)
                POST("", predmetController::createPredmet)
                PUT("", predmetController::updatePredmet)

                "/{id}".nest {
                    GET("", predmetController::findPredmetById)
                    DELETE("", predmetController::deletePredmet)

                    "/teachers".nest {
                        GET("", predmetController::findTeachersByPredmet)
                        POST("", predmetController::addTeachersToPredmet)
                    }
                }
            }
        }
    }
}
