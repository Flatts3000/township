package com.flatts.township.models

import com.flatts.township.interfaces.Job

class JobImpl : Job {
    override var limit: Int = 0
    override var produces: Set<SupplyMarkerImpl> = setOf()
}