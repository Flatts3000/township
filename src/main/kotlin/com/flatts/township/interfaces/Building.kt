package com.flatts.township.interfaces

import com.flatts.township.models.JobImpl
import com.flatts.township.models.SupplyMarkerImpl
import com.flatts.township.models.UnlockMarkerImpl

interface Building {
    var buildDuration: Int
    var consumes: Set<SupplyMarkerImpl>
    var costs: Set<SupplyMarkerImpl>
    var description2: String
    var description: String
    var iconClass: String
    var job: JobImpl?
    var label: String
    var population: Int?
    var unlocked: Boolean
    var unlocks: Set<UnlockMarkerImpl>
}