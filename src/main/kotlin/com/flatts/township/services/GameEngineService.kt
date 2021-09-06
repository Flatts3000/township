package com.flatts.township.services

import com.flatts.township.models.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class GameEngineService(private val gameService: GameService, private val messageService: MessageService, private val buildingService: BuildingService, private val supplyService: SupplyService) {
    private val runningGames = mutableMapOf<String, GameImpl>()

    companion object {
        val log: Logger = LoggerFactory.getLogger(GameEngineService::class.java)
    }

    fun addGame(game: GameImpl) {
        runningGames.putIfAbsent(game.guid, game)
    }

    fun removeGame(game: GameImpl) {
        runningGames.remove(game.guid)
    }

    @Scheduled(fixedDelay = 1000)
    fun tickAll() {
        runningGames.values.forEach {
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

        game.constructionQueue.offer(ConstructionImpl(building))
        messageService.sendSupplyUpdateMessage(dirtyPiles)

        return true
    }

    private fun tick(game: GameImpl) {
        log.info("Ticking game: {}", game)
        tickSupplyPiles(game)
        tickConstructionQueue(game)
    }

    private fun tickConstructionQueue(game: GameImpl) {
        val current = game.constructionQueue.peek() ?: return
        
        current.tick()

        if (current.isCompleted()) {
            constructBuilding(game, current.label)
            game.constructionQueue.remove(current)
        }

        messageService.sendBuilderUpdateMessage(current)
    }

    private fun tickSupplyPiles(game: GameImpl) {
        game.towns.forEach {
            val dirtySupplyPiles = mutableSetOf<SupplyPileImpl>()
            val pileMap: MutableMap<SupplyPileImpl, Double> = mutableMapOf()

            for (kvp in it.buildings) {
                val building = buildingService.findBuilding(kvp.key) ?: continue

                for (marker in building.consumes) {
                    val pile = findSupplyPile(game, marker.supply)

                    if (pile != null) {
                        val a = -marker.quantity * kvp.value
                        pileMap[pile] = a + (pileMap[pile] ?: 0.0)
                    }
                }

                for (marker in building.produces) {
                    val pile = findSupplyPile(game, marker.supply)

                    if (pile != null) {
                        val a = marker.quantity * kvp.value
                        pileMap[pile] = a + (pileMap[pile] ?: 0.0)
                    }
                }
            }

            pileMap.forEach { x ->
                x.key.updateQuantity(x.value)
                dirtySupplyPiles.add(x.key)
            }

            if (dirtySupplyPiles.isNotEmpty()) messageService.sendSupplyUpdateMessage(dirtySupplyPiles)
        }
    }

    private fun unlockObjects(game: GameImpl, building: BuildingImpl) {
        if (building.unlocks.isEmpty()) return

        building.unlocks.forEach {
            when (it.type) {
                "building" -> {
                    val builder = buildingService.findBuilding(it.label) ?: return@forEach
                    if (game.builder.find { b -> b == builder.label } != null) return@forEach
                    game.builder.add(builder.label)
                    messageService.sendNewBuilderMessage(builder)
                }
                "supply" -> {
                    val supply = supplyService.findSupply(it.label) ?: return@forEach
                    if (game.supplyPiles.find { p -> p.label == supply.label } != null) return@forEach
                    game.supplyPiles.add(supply)
                    messageService.sendNewSupplyMessage(supply)
                }
            }
        }
    }

    private fun constructBuilding(game: GameImpl, label: String) {
        val building = buildingService.findBuilding(label) ?: return
        val population = gameService.getPopulation(game)

        // Create a building.
        game.towns.first().buildings.merge(building.label, 1) { a, b -> a + b }

        // Unlock stuff.
        unlockObjects(game, building)

        val newPop = gameService.getPopulation(game)

        messageService.sendNewBuildingMessage(building)
        if (newPop != population) messageService.sendNewPopulationMessage(newPop)
    }

    private fun findGame(guid: String): GameImpl? {
        return runningGames[guid]
    }

    private fun findSupplyPile(game: GameImpl, supply: String): SupplyPileImpl? {
        return game.supplyPiles.firstOrNull { it.label == supply }
    }
}