package com.telemedel

import com.google.gson.Gson
import com.telemedel.data.models.User
import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/user/5f51ea07a98742c17eb19a78").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }

            handleRequest(HttpMethod.Post, "/user") {
                setBody(Gson().toJson(User("djd@djdj.com", "sdfd", "sdfdf")))
                addHeader(HttpHeaders.ContentType,"application/json")
            }.apply {
                assertEquals(HttpStatusCode.Created, response.status())
            }
        }
    }
}
