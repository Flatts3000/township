package com.flatts.township.interfaces

interface SupplyPile {
    val supply: Supply
    val quantity: Int
    val pileSize: Int
    val unlocked: Boolean
}