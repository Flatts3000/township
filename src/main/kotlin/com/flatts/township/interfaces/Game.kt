package com.flatts.township.interfaces

import com.flatts.township.models.BuildingImpl
import com.flatts.township.models.SupplyPileImpl

interface Game {
    var id: String
    var guid: String
    var supplyPiles: MutableList<SupplyPileImpl>
    var buildings: MutableList<BuildingImpl>
    var builder: MutableSet<BuildingImpl>
}