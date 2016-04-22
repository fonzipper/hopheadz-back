package com.hopheadz.data

import org.joda.time.DateTime

/**
 * Created by NS on 06/04/16.
 */

data class Yeast(
        val name: String = "",
        val country: String = "",
        val productionDate: DateTime = DateTime.now(),
        val isLiquid: Boolean = false
)