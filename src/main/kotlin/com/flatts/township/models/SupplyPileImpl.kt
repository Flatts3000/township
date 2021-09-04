package com.flatts.township.models

import com.flatts.township.interfaces.Supply
import com.flatts.township.interfaces.SupplyPile

class SupplyPileImpl(override val supply: SupplyImpl) : SupplyPile {
    override var quantity: Int = 0
    override var pileSize: Int = 0
    override var unlocked: Boolean = false

    init {
        quantity = supply.quantity
        pileSize = supply.pileSize
        unlocked = supply.label == "Food" || supply.label == "Wood"
    }
}