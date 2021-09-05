package com.flatts.township.interfaces

import com.flatts.township.models.ConstructionImpl
import com.flatts.township.models.JobImpl

interface Town {
    var builderLimit: Int
    var buildings: MutableMap<String, Int>
    var constructions: MutableList<ConstructionImpl>
    var guid: String
    var jobs: MutableSet<JobImpl>
    var population: Int
    var populationLimit: Int
    var resources: MutableMap<String, Int>
}