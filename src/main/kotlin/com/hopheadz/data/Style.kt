package com.hopheadz.data

data class Style(
        var name: String = "",
        var country: String = "",
        var family: String = "",
        var minGravity: Float = 0f,
        var maxGravity: Float = 0f,
        var minBitterness: Float = 0f,
        var maxBitterness: Float = 0f,
        var minColor: Float = 0f,
        var maxColor: Float = 0f,
        var minABV: Float = 0f,
        var maxABV: Float = 0f,
        var description: String = ""
)