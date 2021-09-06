package com.flatts.township.models

import com.flatts.township.interfaces.Town

class TownImpl : Town {
    override var builderLimit: Int = 2
    override var buildings: MutableMap<String, Int> = mutableMapOf()
    override var constructions: MutableList<ConstructionImpl> = mutableListOf()
    override var guid: String = ""
    override var jobs: MutableSet<JobImpl> = mutableSetOf()
    override var resources: MutableMap<String, Int> = mutableMapOf()
}