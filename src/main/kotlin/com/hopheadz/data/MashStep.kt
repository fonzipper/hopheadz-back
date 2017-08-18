/**
 * Created by capitan on 8/16/17
 */
package com.hopheadz.data

import javax.persistence.Entity

@Entity
data class MashStep (
    var name: String = "",
    var type: String = "",
    var stepNumber: Int = 0,
    var stepTemperature: Float = 0f,
    var time: Int = 0,
    var ratio: Float = 0f
)