package com.flatts.township.models

import com.flatts.township.interfaces.SupplyMarker

class SupplyMarkerImpl : SupplyMarker {
    override var supply: String = ""
    override var iconClass: String = ""
    override var quantity: Int = 0
}