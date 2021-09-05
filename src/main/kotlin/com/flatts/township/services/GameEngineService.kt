package com.flatts.township.services

import com.flatts.township.models.GameImpl
import com.flatts.township.models.SupplyPileImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class GameEngineService(private val gameService: GameService, private val messageService: MessageService, private val buildingService: BuildingService) {
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

    fun build(guid: String, label: String): Boolean {
        val game = findGame(guid) ?: return false
        val building = buildingService.findBuilding(label) ?: return false
        val dirtyPiles = mutableSetOf<SupplyPileImpl>()
        
        // Validate that all costs can be paid.
        building.costs.forEach { 
            val pile = findSupplyPile(game, it.supply) ?: return false
            dirtyPiles.add(pile)
            if (pile.quantity < it.quantity) return false
        }
        
        // Pay all costs.
        building.costs.forEach {
            val pile = findSupplyPile(game, it.supply) ?: return false
            pile.updateQuantity(-it.quantity)
        }
        
        // Create a building.
        game.town.buildings.add(building)
        
        messageService.sendSupplyUpdateMessage(dirtyPiles)
        messageService.sendNewBuildingMessage(building)
        
        return true
    }

    private fun tick(game: GameImpl) {
        log.info("Ticking game: {}", game)
        tickSupplyPiles(game)
    }

    private fun tickSupplyPiles(game: GameImpl) {
        val dirtySupplyPiles = mutableSetOf<SupplyPileImpl>()

        for (building in game.town.buildings) {
            for (marker in building.produces) {
                val pile = findSupplyPile(game, marker.supply)

                if (pile != null) {
                    pile.updateQuantity(marker.quantity)
                    dirtySupplyPiles.add(pile)
                }
            }

            for (marker in building.consumes) {
                val pile = findSupplyPile(game, marker.supply)

                if (pile != null) {
                    pile.updateQuantity(-marker.quantity)
                    dirtySupplyPiles.add(pile)
                }
            }
        }

        if (dirtySupplyPiles.isNotEmpty()) messageService.sendSupplyUpdateMessage(dirtySupplyPiles)
    }

    private fun findGame(guid: String): GameImpl? {
        return runningGames.find { it.guid == guid }
    }

    private fun findSupplyPile(game: GameImpl, supply: String): SupplyPileImpl? {
        return game.supplyPiles.firstOrNull { it.supply.label == supply }
    }
}