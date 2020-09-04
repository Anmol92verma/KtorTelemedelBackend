package com.telemedel.data

import com.mongodb.client.MongoCollection
import com.telemedel.routes.users.TELEMEDEL
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

object TMMongoDBClient {

    private val mongoClient = KMongo.createClient()
    val database = mongoClient.getDatabase(TELEMEDEL)

    inline fun <reified T : Any> getCollection(): MongoCollection<T> {
        return database.getCollection<T>()
    }
}
