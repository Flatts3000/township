package com.flatts.township.models

import com.flatts.township.interfaces.Building

class BuildingImpl : Building {
    override var job: JobImpl? = null
    override var buildDuration: Int = 0
    override var consumes: Set<SupplyMarkerImpl> = setOf()
    override var costs: Set<SupplyMarkerImpl> = setOf()
    override var description2: String = ""
    override var description: String = ""
    override var iconClass: String = ""
    override var label: String = ""
    override var population: Int? = null
    override var unlocked: Boolean = false
    override var unlocks: Set<UnlockMarkerImpl> = setOf()

    override fun toString(): String {
        return label
    }
}