package com.flatts.township.interfaces

import com.flatts.township.models.SupplyImpl

interface SupplyPile {
    fun updateQuantity(i: Int)

    val supply: SupplyImpl
    val quantity: Int
    val pileSize: Int
    val unlocked: Boolean
}