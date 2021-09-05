package com.flatts.township.services

import com.flatts.township.dtos.SupplyPileUpdateDTO
import com.flatts.township.models.SupplyPileImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class MessageService(private val template: SimpMessagingTemplate) {
    fun sendSupplyUpdateMessage(dirtySupplyPiles: Set<SupplyPileImpl>) {
        this.template.convertAndSend("/topic/game", SupplyPileUpdateDTO(dirtySupplyPiles))
    }
}