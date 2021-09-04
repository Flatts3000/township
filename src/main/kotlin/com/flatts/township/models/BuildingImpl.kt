package com.flatts.township.models

import com.flatts.township.interfaces.Building

class BuildingImpl : Building {
    override var label: String = ""
    override var description: String = ""
    override var costs: Set<SupplyMarkerImpl> = setOf()
    override var produces: Set<SupplyMarkerImpl> = setOf()
    override var unlocked: Boolean = false
    override var iconClass: String = ""
}