package com.hopheadz.data

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by NS on 31/03/16.
 */

data class Recipe(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val Id: Long = 0,
        val style: Style = Style(id = 0,
                name = "",
                maxABV = 0f,
                minABV = 0f,
                maxBitterness = 0f,
                minBitterness = 0f,
                maxColor = 0f,
                minColor = 0f,
                maxGravity = 0f,
                minGravity = 0f),
        val volume: Float = 0f,
        val malts: Array<Malt> = arrayOf(Malt(
                id = 0,
                name = "",
                country = "",
                color = 0f,
                efficiency = 0f,
                fermentability = 0f,
                description = "")),
        val hops: Array<Hop> = arrayOf(Hop(
                id = 0,
                name = "",
                country = "",
                alpha = 0f,
                desciption = ""
        )),
        val gravity: Float = 0f,
        val color: Float = 0f,
        val bitterness: Float = 0f,
        val abv: Float = 0f,
        val efficiency: Float = 0f
)