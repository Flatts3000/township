package com.flatts.township.services

import com.flatts.township.models.GameImpl
import com.flatts.township.models.SupplyPileImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class GameEngineService(private val gameService: GameService) {
    private val runningGames = mutableSetOf<GameImpl>()

    companion object {
        val log: Logger = LoggerFactory.getLogger(GameEngineService::class.java)
    }

    fun addGame(game: GameImpl) {
        runningGames.add(game)
    }

    @Scheduled(fixedRate = 1000)
    fun tickAll() {
        runningGames.forEach {
            tick(it)
            gameService.saveGame(it)
        }
    }

    private fun tick(game: GameImpl) {
        log.info("Ticking game: {}", game)
        updateSupplyPiles(game)
    }

    private fun updateSupplyPiles(game: GameImpl) {
        for (building in game.town.buildings) {
            for (marker in building.produces) {
                val pile = findSupplyPile(game, marker.supply)
                pile?.updateQuantity(marker.quantity)
            }

            for (marker in building.consumes) {
                val pile = findSupplyPile(game, marker.supply)
                pile?.updateQuantity(-marker.quantity)
            }
        }
    }

    private fun findSupplyPile(game: GameImpl, supply: String): SupplyPileImpl? {
        return game.supplyPiles.firstOrNull { it.supply.label == supply }
    }
}