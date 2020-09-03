package com.telemedel.routes.users

import com.telemedel.data.mongo.MongoDataService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

const val COLL_USERS: String = "USERS"

fun Route.getUser(mongoDataService: MongoDataService) {
    get("/{id?}") {
        print("got the /user/id")
        val userid = call.parameters["id"]
        print("got the /user/$userid")
        userid?.let { it1 -> mongoDataService.documentFromCollection(it1, COLL_USERS) }
        call.respond(HttpStatusCode.OK, "woo hooo $userid")
    }
}
