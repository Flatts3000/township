package com.flatts.township.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.flatts.township.interfaces.Construction

@JsonIgnoreProperties(value = ["building"])
class ConstructionImpl(val building: BuildingImpl?) : Construction {
    override var costs: MutableSet<SupplyMarkerImpl> = mutableSetOf()
    override var duration: Int = 0
    override var label: String = ""
    override var progress: Int = 0

    init {
        costs = building?.costs?.toMutableSet() ?: mutableSetOf()
        duration = building?.buildDuration ?: 0
        label = building?.label ?: ""
    }

    override fun isCompleted(): Boolean {
        return progress >= duration
    }

    override fun tick() {
        if (progress == duration) return
        progress++
    }
}