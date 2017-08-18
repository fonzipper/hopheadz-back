package com.hopheadz.data
import javax.persistence.Entity

@Entity
data class MashType(
        var name: String = "",
        var grainTemperature: Float = 0f,
        var description: String = "",
        var mashSteps: Array<MashStep> = arrayOf(),
        var mashSequence: String = ""
)