package com.flatts.township.models

import com.flatts.township.interfaces.Supply

class SupplyImpl : Supply {
    override var label: String = ""
    override val order: Int = 0
    override val iconClass: String = ""
    override val initialQuantity: Int = 0
    override val initialPileSize: Int = 0
}