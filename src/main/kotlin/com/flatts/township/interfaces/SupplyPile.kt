package com.flatts.township.interfaces

import com.flatts.township.models.SupplyImpl

interface SupplyPile {
    fun updateQuantity(i: Int)

    var supply: SupplyImpl
    var quantity: Int
    var pileSize: Int
}