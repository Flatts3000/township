package com.flatts.township.dtos

import com.flatts.township.models.ConstructionImpl

class BuilderUpdateDTO(val current: ConstructionImpl) {
    val type: String = "BuilderUpdate"
}