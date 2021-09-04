package com.flatts.township.models

import com.flatts.township.interfaces.Town

class TownImpl : Town {
    override var buildings: MutableSet<BuildingImpl> = mutableSetOf()
}