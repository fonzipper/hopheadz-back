package com.hopheadz.data

/**
 * Created by capitan on 7/24/17.
 */
data class BrewSetup(
        val owner: String = "",
        val mashWaterLossCoeff: Float = 0f,
        val evaporationRate: Float = 0f,
        val equipmentLoss: Float = 0f,
        val tunDeadSpaceVolume: Float = 0f,
        val spargeWaterTemperature: Float = 0f,
        val mashEfficiency: Float = 0f
)