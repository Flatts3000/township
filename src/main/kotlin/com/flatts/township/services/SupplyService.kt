package com.flatts.township.services

import com.flatts.township.interfaces.Supply
import com.flatts.township.interfaces.SupplyPile
import com.flatts.township.services.SupplyService
import com.google.gson.Gson
import com.flatts.township.models.SupplyImpl
import com.flatts.township.models.SupplyPileImpl
import com.google.gson.reflect.TypeToken
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.lang.Exception
import java.nio.file.Files
import java.util.Comparator
import java.util.stream.Collectors

@Service
class SupplyService {
    private val supplyList: List<SupplyImpl>

    companion object {
        val log: Logger = LoggerFactory.getLogger(SupplyService::class.java)
    }

    init {
        val gson = Gson()
        var json = ""

        try {
            val file = ResourceUtils.getFile("classpath:configs/supplies.json")
            json = String(Files.readAllBytes(file.toPath()))
        } catch (e: Exception) {
            log.error("There was an exception loading the SupplyService: {}", e.toString(), e)
        }

        val unsorted: List<SupplyImpl> = gson.fromJson(json, object : TypeToken<List<SupplyImpl?>?>() {}.type)
        supplyList = unsorted.stream().sorted(Comparator.comparingInt(SupplyImpl::order)).collect(Collectors.toList())
        log.info("Loaded {} supplies", supplyList.size)
    }

    fun buildSupplyPiles(): MutableList<SupplyPileImpl> {
        val supplyPiles: MutableList<SupplyPileImpl> = arrayListOf()

        supplyList.forEach {
            if (it.unlocked) supplyPiles.add(SupplyPileImpl(it))
        }

        return supplyPiles
    }

    fun findSupply(label: String): SupplyPileImpl? {
        val supply = supplyList.find { it.label == label } ?: return null
        return SupplyPileImpl(supply)
    }
}