package com.flatts.township.interfaces

import com.flatts.township.models.SupplyMarkerImpl

interface Building {
    val label: String
    val iconClass: String
    val costs: Set<SupplyMarkerImpl>
    val produces: Set<SupplyMarkerImpl>
    val unlocked: Boolean
    val description: String
    var consumes: Set<SupplyMarkerImpl>
}