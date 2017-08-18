package com.hopheadz.data
import javax.persistence.Entity

@Entity
data class Malt(
        var name: String = "",
        var country: String = "",
        var color: Float = 0F,
        var efficiency: Int = 0,
        var fermentability: Float = 0F,
        var description: String = ""
)