package com.flatts.township.services

import com.flatts.township.interfaces.Building
import com.flatts.township.interfaces.Town
import com.flatts.township.models.BuildingImpl
import com.flatts.township.models.TownImpl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.lang.Exception
import java.nio.file.Files

@Service
class BuildingService {
    private val buildingsSet: Set<Building>

    companion object {
        val log: Logger = LoggerFactory.getLogger(BuildingService::class.java)
    }

    init {
        val gson = Gson()
        var json = ""

        try {
            val file = ResourceUtils.getFile("classpath:configs/buildings.json")
            json = String(Files.readAllBytes(file.toPath()))
        } catch (e: Exception) {
            log.error("There was an exception loading the BuildingService: {}", e.toString(), e)
        }

        buildingsSet = gson.fromJson(json, object : TypeToken<Set<BuildingImpl?>?>() {}.type)
        log.info("Loaded {} buildings", buildingsSet.size)
    }

    fun getBuildings(): Set<Building> {
        return buildingsSet
    }

    fun buildTown(): Town {
        val town = TownImpl()

        for (building in buildingsSet) {
            if (building.label == "Farm") town.buildings.add(building)
            if (building.label == "Tent") town.buildings.add(building)
        }

        return town
    }
}