package com.flatts.township.dtos

import com.flatts.township.models.SupplyPileImpl

class SupplyPileUpdateDTO(val supplyPiles: Set<SupplyPileImpl>) {
    val type: String = "SupplyPileUpdate"
}