package com.hopheadz.data

import javax.persistence.Entity

@Entity
data class StepDescription (
        var name: String = "",
        var temperature: Float = 0f,
        var time: Int = 0
)