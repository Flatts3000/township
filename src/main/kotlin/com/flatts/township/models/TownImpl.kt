package com.flatts.township.models

import com.flatts.township.interfaces.Town

class TownImpl : Town {
    override var builderLimit: Int = 2
    override var buildings: MutableMap<String, Int> = mutableMapOf()
    override var constructions: MutableList<ConstructionImpl> = mutableListOf()
    override var guid: String = ""
    override var jobLimits: MutableMap<String, Int> = mutableMapOf()
    override var jobs: MutableMap<String, Int> = mutableMapOf()
    override var resources: MutableMap<String, Int> = mutableMapOf()
}