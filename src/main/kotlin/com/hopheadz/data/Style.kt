package com.hopheadz.data

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by NS on 31/03/16.
 */

data class Style(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,
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