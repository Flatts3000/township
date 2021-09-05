package com.flatts.township.services

import com.flatts.township.dtos.NewBuildingUpdateDTO
import com.flatts.township.dtos.SupplyPileUpdateDTO
import com.flatts.township.models.BuildingImpl
import com.flatts.township.models.SupplyPileImpl
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class MessageService(private val template: SimpMessagingTemplate) {
    fun sendSupplyUpdateMessage(dirtySupplyPiles: Set<SupplyPileImpl>) {
        this.template.convertAndSend("/topic/game", SupplyPileUpdateDTO(dirtySupplyPiles))
    }

    fun sendNewBuildingMessage(building: BuildingImpl) {
        this.template.convertAndSend("/topic/game", NewBuildingUpdateDTO(building))
    }
}