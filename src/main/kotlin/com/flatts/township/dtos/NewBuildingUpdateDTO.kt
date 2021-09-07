package com.flatts.township.dtos

import com.flatts.township.models.BuildingImpl

class NewBuildingUpdateDTO(val building: BuildingImpl, val jobLimit: Int) {
    val type: String = "NewBuildingUpdate"
}