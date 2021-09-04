package com.flatts.township.interfaces

interface Building {
    val label: String
    val iconClass: String
    val costs: Set<SupplyMarker>
    val produces: Set<SupplyMarker>
    val unlocked: Boolean
    val description: String
}