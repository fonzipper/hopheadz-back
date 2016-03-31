package com.hopheadz.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by NS on 30/03/16.
 */
@Entity
data class Malt(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val name: String = "",
    val country: String = "",
    val color: Float = 0.0f,
    val efficiency: Float = 0.0f,
    val fermentability: Float = 0.0f,
    val description: String = ""
)