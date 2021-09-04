package com.flatts.township.interfaces

import com.flatts.township.models.SupplyImpl

interface SupplyPile {
    val supply: SupplyImpl
    val quantity: Int
    val pileSize: Int
    val unlocked: Boolean
}