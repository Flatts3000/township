package com.flatts.township.services

import com.flatts.township.models.GameImpl
import org.springframework.stereotype.Service

@Service
class TownService {
    fun getTotalJobCount(game: GameImpl): Int {
        var i = 0
        game.towns.forEach { town ->
            town.jobs.forEach {
                i += it.value
            }
        }
        return i
    }
}