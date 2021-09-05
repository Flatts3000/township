package com.flatts.township.interfaces

interface Construction {
    var costs: MutableSet<SupplyMarker>
    var duration: Int
    var label: String
    var progress: Int
}