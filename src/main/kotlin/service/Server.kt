package service

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import service.models.List
import service.models.Task
import service.repositories.ListRepository
import service.repositories.TaskRepository

fun launch() {
    embeddedServer(Netty, port = 8888) {
        install(ContentNegotiation) {
            gson { }
        }

        routing {
            get("/") {
                call.respond("Welcome to RESTful API")
            }

            route("api/v1") {
                route("lists") {
                    get {
                        call.respond(ListRepository.findAll())
                    }

                    get("/{id}") {
                        val listId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
                        call.respond(ListRepository.findOneById(listId)!!)
                    }

                    post {
                        val list = call.receive<List>()
                        ListRepository.insertOne(list)
                        call.respond(HttpStatusCode.Created)
                    }

                    delete("/{id}") {
                        val listId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
                        ListRepository.deleteOneById(listId)
                        call.respond(HttpStatusCode.OK)
                    }

                    put {
                        val list = call.receive<List>()
                        ListRepository.updateOne(list)
                        call.respond(HttpStatusCode.OK)
                    }
                }

                route("tasks") {
                    get {
                        call.respond(TaskRepository.findAll())
                    }

                    get("/{id}") {
                        val taskId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
                        call.respond(TaskRepository.findOneById(taskId)!!)
                    }

                    post {
                        val task = call.receive<Task>()
                        TaskRepository.insertOne(task)
                        call.respond(HttpStatusCode.Created)
                    }

                    delete("/{id}") {
                        val taskId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
                        TaskRepository.deleteOneById(taskId)
                        call.respond(HttpStatusCode.OK)
                    }

                    put {
                        val task = call.receive<Task>()
                        TaskRepository.updateOne(task)
                        call.respond(HttpStatusCode.OK)
                    }
                }
            }
        }
    }.start(wait = true)
}