package com.flatts.township.interfaces

import com.flatts.township.models.SupplyMarkerImpl

interface Job {
    var limit: Int
    var produces: Set<SupplyMarkerImpl>
}