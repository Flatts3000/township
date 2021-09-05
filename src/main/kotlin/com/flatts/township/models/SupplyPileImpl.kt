package com.flatts.township.models

import com.flatts.township.interfaces.Supply
import com.flatts.township.interfaces.SupplyPile

class SupplyPileImpl(val supply: SupplyImpl) : SupplyPile {
    override var label: String = ""
    override var quantity: Double = 0.0
    override var pileSize: Int = 0

    init {
        label = supply.label
        quantity = supply.quantity.toDouble()
        pileSize = supply.pileSize
    }

    override fun updateQuantity(i: Double) {
        quantity = (quantity + i).coerceIn(0.0, pileSize.toDouble())
    }

    override fun toString(): String {
        return "SupplyPile(label='$label', quantity=$quantity, pileSize=$pileSize)"
    }
}