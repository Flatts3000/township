package com.flatts.township.models

import com.flatts.township.interfaces.Game
import java.io.Serializable

class GameImpl : Game, Serializable {
    override var id: String = ""
    override var guid: String = ""
    override var town: TownImpl = TownImpl()
    override var supplyPiles: List<SupplyPileImpl> = listOf()
    override fun toString(): String {
        return "GameImpl(guid='$guid', town=$town, supplyPiles=$supplyPiles)"
    }
}