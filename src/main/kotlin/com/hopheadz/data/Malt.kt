package com.hopheadz.data

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by NS on 30/03/16.
 */

@Entity
data class Malt(
        var name: String = "",
        var country: String = "",
        var color: Float = 0F,
        var efficiency: Int = 0,
        var fermentability: Float = 0F,
        var description: String = ""
)