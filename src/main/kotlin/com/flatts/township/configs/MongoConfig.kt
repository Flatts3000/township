package com.flatts.township.configs

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import com.mongodb.client.MongoClient
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.Throws
import org.springframework.data.mongodb.core.MongoTemplate
import java.lang.Exception

@Configuration
@EnableScheduling
@EnableMongoRepositories(basePackages = ["com.flatts.township.repositories"])
class MongoConfig {
    @Bean
    fun mongo(): MongoClient {
        val connectionString = ConnectionString("mongodb://localhost:27017/township")
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return MongoClients.create(mongoClientSettings)
    }

    @Bean
    @Throws(Exception::class)
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongo(), "township")
    }
}