package com.flatts.township.dtos

import com.flatts.township.models.BuildingImpl

class NewBuildingUpdateDTO(val building: BuildingImpl) {
    val type: String = "NewBuildingUpdate"
}