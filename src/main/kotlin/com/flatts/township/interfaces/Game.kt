package com.flatts.township.interfaces

import com.flatts.township.models.SupplyPileImpl
import com.flatts.township.models.TownImpl

interface Game {
    var id: String
    var guid: String
    var town: TownImpl
    var supplyPiles: List<SupplyPileImpl>
}