package com.flatts.township.models

import com.flatts.township.interfaces.Building

class BuildingImpl : Building {
    override var buildDuration: Int = 0
    override var label: String = ""
    override var description: String = ""
    override var description2: String = ""
    override var costs: Set<SupplyMarkerImpl> = setOf()
    override var produces: Set<SupplyMarkerImpl> = setOf()
    override var consumes: Set<SupplyMarkerImpl> = setOf()
    override var unlocks: Set<UnlockMarkerImpl> = setOf()
    override var unlocked: Boolean = false
    override var iconClass: String = ""

    override fun toString(): String {
        return label
    }
}