package com.flatts.township.controllers

import com.flatts.township.interfaces.Game
import com.flatts.township.models.GameImpl
import com.flatts.township.services.BuildingService
import com.flatts.township.services.GameService
import com.flatts.township.services.SupplyService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.thymeleaf.util.StringUtils

@Controller
class HomeController(private val gameService: GameService, private val buildingService: BuildingService) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(HomeController::class.java)
    }
    
    @GetMapping("")
    fun index(model: Model): String {
        model.addAttribute("game", gameService.findGame(gameService.defaultId))
        model.addAttribute("buildings", buildingService.getBuildings())
        return "index"
    }

    @PostMapping(path = ["/save"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody game: GameImpl): ResponseEntity<Boolean> {
        if (StringUtils.isEmpty(game.guid)) {
            log.error("Save: missing guid")
            return ResponseEntity(false, HttpStatus.BAD_REQUEST)
        }

        gameService.saveGame(game)
        return ResponseEntity(true, HttpStatus.OK)
    }
}