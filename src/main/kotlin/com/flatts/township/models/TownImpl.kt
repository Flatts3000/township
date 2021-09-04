package com.flatts.township.models

import com.flatts.township.interfaces.Building
import com.flatts.township.interfaces.Town

class TownImpl : Town {
    override val buildings: MutableSet<Building> = mutableSetOf()
}