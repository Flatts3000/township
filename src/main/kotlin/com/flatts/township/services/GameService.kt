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

    fun findGame(guid: String): GameImpl {
        val game = gameRepository.findByGuid(guid)

        if (game != null) {
            return game
        }
        
        createGame()

        return findGame(defaultId)
    }

    fun createGame() {
        val game = GameImpl()
        game.guid = defaultId
        game.towns = buildingService.buildTowns()
        game.builder = buildingService.buildBuilder()
        game.supplyPiles = supplyService.buildSupplyPiles()
        log.info("Creating new game: {}", game)
        saveGame(game)
    }

    fun saveGame(game: GameImpl) {
        log.info("Saving game: {}", game)

        val existingGame = gameRepository.findByGuid(game.guid)

        if (existingGame == null) {
            gameRepository.save(game)
            return
        }

        existingGame.towns = game.towns
        existingGame.builder = game.builder
        existingGame.supplyPiles = game.supplyPiles

        gameRepository.save(existingGame)
    }

    fun resetGame(game: GameImpl) {
        log.info("Resetting game: {}", game)
        gameRepository.deleteByGuid(game.guid)
    }
}