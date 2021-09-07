package com.flatts.township.interfaces

import com.flatts.township.models.ConstructionImpl

interface Town {
    var builderLimit: Int
    var buildings: MutableMap<String, Int>
    var constructions: MutableList<ConstructionImpl>
    var guid: String
    var jobLimits: MutableMap<String, Int>
    var jobs: MutableMap<String, Int>
    var resources: MutableMap<String, Int>
}