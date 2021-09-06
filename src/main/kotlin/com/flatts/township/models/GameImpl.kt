package com.flatts.township.models

import com.flatts.township.interfaces.Game
import java.io.Serializable
import java.time.Instant

class GameImpl : Game, Serializable {
    override var constructionQueue = ConstructionQueue()
    override var builder: MutableList<String> = mutableListOf()
    override var created: Instant = Instant.now()
    override var guid: String = ""
    override var id: String = ""
    override var supplyPiles: MutableList<SupplyPileImpl> = mutableListOf()
    override var towns: MutableList<TownImpl> = mutableListOf()
    override fun toString(): String {
        return "GameImpl(guid='$guid',created='$created')"
    }
    
    fun inherit(o: GameImpl) {
        this.constructionQueue = o.constructionQueue
        this.builder = o.builder
        this.supplyPiles = o.supplyPiles
        this.towns = o.towns
    }
}