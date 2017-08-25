package com.hopheadz.data

import org.joda.time.DateTime

/**
 * Created by NS on 06/04/16.
 */

data class Yeast(
        var name: String = "",
        var manufacturer: String = "",
        var country: String = "",
        var productionDate: DateTime = DateTime.now(),
        var isLiquid: Boolean = false
)