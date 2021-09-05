package com.flatts.township.models

import com.flatts.township.interfaces.Supply
import com.flatts.township.interfaces.SupplyPile

class SupplyPileImpl(override var supply: SupplyImpl) : SupplyPile {
    override var quantity: Int = 0
    override var pileSize: Int = 0

    init {
        quantity = supply.quantity
        pileSize = supply.pileSize
    }

    override fun updateQuantity(i: Int) {
        quantity = (quantity + i).coerceIn(0, pileSize)
    }

    override fun toString(): String {
        return supply.label
    }
}