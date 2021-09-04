package com.flatts.township.interfaces

import com.flatts.township.models.BuildingImpl

interface Town {
    val buildings: Set<BuildingImpl>
}