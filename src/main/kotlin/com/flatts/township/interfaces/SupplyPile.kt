package com.flatts.township.interfaces

interface SupplyPile {
    var label: String
    var pileSize: Int
    var quantity: Double

    fun updateQuantity(i: Double)
}