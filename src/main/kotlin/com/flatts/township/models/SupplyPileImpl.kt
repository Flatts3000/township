package com.flatts.township.models

import com.flatts.township.interfaces.Supply
import com.flatts.township.interfaces.SupplyPile

class SupplyPileImpl(override val supply: Supply) : SupplyPile {
    override var quantity: Int = 0
    override var pileSize: Int = 0

    init {
        this.quantity = supply.initialQuantity
        this.pileSize = supply.initialPileSize
    }
}