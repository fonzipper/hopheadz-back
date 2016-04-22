package com.hopheadz.data

import org.joda.time.DateTime
import java.math.BigDecimal

/**
 * Created by NS on 31/03/16.
 */

data class Recipe(
        val style: Style? = null,
        val volume: Float = 0f,
        val malts: Array<Malt>? = null,
        val hops: Array<Hop>? = null,
        var yeasts: Array<Yeast>? = null,
        val gravity: Float = 0f,
        val color: Float = 0f,
        val bitterness: Float = 0f,
        val abv: Float = 0f,
        val efficiency: Float = 0f,
        val authorID: String = "",
        val created: DateTime = DateTime.now(),
        val isPublic: Boolean = true
)