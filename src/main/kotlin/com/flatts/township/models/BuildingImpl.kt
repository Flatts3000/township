package com.flatts.township.models

import com.flatts.township.interfaces.Building

class BuildingImpl : Building {
    override val label: String = ""
    override val description: String = ""
    override val costs: Set<SupplyMarkerImpl> = setOf()
    override val produces: Set<SupplyMarkerImpl> = setOf()
    override var unlocked: Boolean = false
    override val iconClass: String = ""
}