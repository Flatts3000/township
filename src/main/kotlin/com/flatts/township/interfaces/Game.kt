package com.flatts.township.interfaces

import com.flatts.township.models.ConstructionQueue
import com.flatts.township.models.SupplyPileImpl
import com.flatts.township.models.TownImpl
import java.time.Instant

interface Game {
    val created: Instant
    var builder: MutableList<String>
    var guid: String
    var id: String
    var supplyPiles: MutableList<SupplyPileImpl>
    var towns: MutableList<TownImpl>
    var constructionQueue: ConstructionQueue
}