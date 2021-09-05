package com.flatts.township.dtos

import com.flatts.township.models.BuildingImpl

class NewBuilderUpdateDTO(val building: BuildingImpl) {
    val type: String = "NewBuilderUpdate"
}