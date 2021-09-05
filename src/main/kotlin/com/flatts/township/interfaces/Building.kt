package com.flatts.township.interfaces

import com.flatts.township.models.SupplyMarkerImpl
import com.flatts.township.models.UnlockMarkerImpl

interface Building {
    var label: String
    var iconClass: String
    var costs: Set<SupplyMarkerImpl>
    var produces: Set<SupplyMarkerImpl>
    var unlocked: Boolean
    var description: String
    var consumes: Set<SupplyMarkerImpl>
    var unlocks: Set<UnlockMarkerImpl>
}