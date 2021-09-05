package com.flatts.township.dtos

import com.flatts.township.models.SupplyPileImpl

class NewSupplyUpdateDTO(val supplyPile: SupplyPileImpl) {
    val type: String = "NewSupplyUpdate"
}