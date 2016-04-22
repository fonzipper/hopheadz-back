package com.hopheadz.data

/**
 * Created by NS on 31/03/16.
 */

data class Style(
        val name: String = "",
        val minGravity: Float = 0f,
        val maxGravity: Float = 0f,
        val minBitterness: Float = 0f,
        val maxBitterness: Float = 0f,
        val minColor: Float = 0f,
        val maxColor: Float = 0f,
        val minABV: Float = 0f,
        val maxABV: Float = 0f,
        val description: String = ""
)