package com.flatts.township.interfaces

import com.flatts.township.models.SupplyMarkerImpl

interface Construction {
    var costs: MutableSet<SupplyMarkerImpl>
    var duration: Int
    var label: String
    var progress: Int
    fun isCompleted(): Boolean
    fun tick()
}