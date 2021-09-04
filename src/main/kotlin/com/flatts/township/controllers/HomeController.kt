﻿package com.flatts.township.controllers

import com.flatts.township.services.BuildingService
import com.flatts.township.services.SupplyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class HomeController(private val supplyService: SupplyService, private val buildingService: BuildingService) {
    @GetMapping("")
    fun index(model: Model): String {
        model.addAttribute("supplyPiles", supplyService.buildSupplyPiles())
        model.addAttribute("buildings", buildingService.getBuildings())
        model.addAttribute("town", buildingService.buildTown())
        return "index"
    }
}