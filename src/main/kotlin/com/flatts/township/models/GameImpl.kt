package com.flatts.township.models

import com.flatts.township.interfaces.Game
import java.io.Serializable

class GameImpl : Game, Serializable {
    override var id: String = ""
    override var guid: String = ""
    override var builder: MutableSet<BuildingImpl> = mutableSetOf()
    override var buildings: MutableList<BuildingImpl> = mutableListOf()
    override var supplyPiles: MutableList<SupplyPileImpl> = mutableListOf()
    override fun toString(): String {
        return "GameImpl(guid='$guid')"
    }
}