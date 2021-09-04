package com.flatts.township.services

import com.flatts.township.models.GameImpl
import com.flatts.township.repositories.GameRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class GameService(private val gameRepository: GameRepository, private val supplyService: SupplyService, private val buildingService: BuildingService) {
    val defaultId: String = "f4991175-942e-457e-aee1-ee2e8b471d33"

    companion object {
        val log: Logger = LoggerFactory.getLogger(GameService::class.java)
    }

    fun findGame(id: String): GameImpl? {
        var game = gameRepository.findByGuid(id)

        if (game != null) return game

        game = GameImpl()
        game.guid = defaultId
        game.town = buildingService.buildTown()
        game.supplyPiles = supplyService.buildSupplyPiles()
        log.info("Creating new game: {}", game)
        return game
    }

    fun saveGame(game: GameImpl) {
        log.info("Saving game: {}", game)
        
        val existingGame = gameRepository.findByGuid(game.guid)
        
        if (existingGame == null) {
            gameRepository.save(game)
            return
        }
        
        existingGame.town = game.town
        existingGame.supplyPiles = game.supplyPiles

        gameRepository.save(existingGame)
    }
}