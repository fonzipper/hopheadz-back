package com.hopheadz.data
import javax.persistence.Entity

@Entity
data class Malt(
        var name: String = "",
        var country: String = "",
        var color: Float = 0F,
        var efficiency: Int = 0,
        var description: String = "",
        var needMash: Boolean = true,
        var type: String = "",
        var fermenatble: Boolean = true
)