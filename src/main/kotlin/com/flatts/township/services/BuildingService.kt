package com.flatts.township.services

import com.flatts.township.interfaces.Building
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
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

@Service
class BuildingService {
    private val buildingsMap: Map<String, BuildingImpl>

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

        val buildingsSet: Set<BuildingImpl> = gson.fromJson(json, object : TypeToken<Set<BuildingImpl>>() {}.type)
        buildingsMap = buildingsSet.stream().collect(Collectors.toMap(BuildingImpl::label, Function.identity()))
        log.info("Loaded {} buildings", buildingsSet.size)
    }

    fun getBuildings(): Map<String, BuildingImpl> {
        return buildingsMap
    }

    fun findBuilding(label: String): BuildingImpl? {
        return buildingsMap[label]
    }

    fun buildTowns(): MutableList<TownImpl> {
        val list = mutableListOf<TownImpl>()
        val town = TownImpl()

        town.buildings["Farm"] = 1
        town.buildings["Tent"] = 1

        list.add(town)

        return list
    }

    fun buildBuilder(): MutableList<String> {
        return mutableListOf("Tent", "Farm", "Woodcutter")
    }
}