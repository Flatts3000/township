package com.flatts.township.models

import com.flatts.township.interfaces.Job

class JobImpl : Job {
    override var label: String = ""
    override var quantity: Int = 0
    override var limit: Int = 0
}