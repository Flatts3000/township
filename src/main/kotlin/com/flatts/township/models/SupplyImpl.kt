package com.flatts.township.models

import com.flatts.township.interfaces.Supply

class SupplyImpl : Supply {
    override var label: String = ""
    override var order: Int = 0
    override var iconClass: String = ""
    override var quantity: Int = 0
    override var pileSize: Int = 0
    override var unlocked: Boolean = false

    override fun toString(): String {
        return label
    }
}