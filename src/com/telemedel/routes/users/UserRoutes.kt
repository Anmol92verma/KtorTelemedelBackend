package com.telemedel.routes.users

import com.telemedel.data.TMMongoDBClient
import com.telemedel.data.models.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.findOneById

const val TELEMEDEL: String = "telemedel"

fun Route.getUser() {
    get("/{id?}") {
        print("got the /user/id")
        val userid = call.parameters["id"]
        val document = userCollection().findOneById(ObjectId(userid))
        document?.let { it1 -> call.respond(HttpStatusCode.OK, it1) }
    }

    post {
        val userCreateParams = call.receive<User>()
        val document = userCollection().findOne(User::email eq userCreateParams.email)
        if (document == null) {
            val result = userCollection().insertOne(userCreateParams)
            call.respond(HttpStatusCode.Created, "saved user $result")
        } else {
            call.respond(HttpStatusCode.Conflict, "already exist")
        }
    }
}

private fun userCollection() = TMMongoDBClient.getCollection<User>()
