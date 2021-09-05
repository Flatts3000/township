package com.flatts.township.models

import com.flatts.township.interfaces.Construction
import com.flatts.township.interfaces.SupplyMarker

class ConstructionImpl: Construction {
    override var costs: MutableSet<SupplyMarker> = mutableSetOf()
    override var duration: Int = 0
    override var label: String = ""
    override var progress: Int = 0
}