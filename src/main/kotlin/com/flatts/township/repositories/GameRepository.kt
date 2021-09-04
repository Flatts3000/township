package com.flatts.township.repositories

import org.springframework.data.repository.CrudRepository
import com.flatts.township.interfaces.Game
import com.flatts.township.models.GameImpl
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : MongoRepository<GameImpl, String> {
    fun findByGuid(id: String): GameImpl?
}