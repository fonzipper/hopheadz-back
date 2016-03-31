package com.hopheadz.data

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by NS on 31/03/16.
 */

data class Hop(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,
        val name: String = "",
        val country: String = "",
        val alpha: Float = 0f,
        val desciption: String = ""
)