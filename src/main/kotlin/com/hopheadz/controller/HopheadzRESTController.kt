package com.hopheadz.controller

import com.hopheadz.data.Greeting
import com.hopheadz.data.Malt
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by NS on 30/03/16.
 */

@RestController
class HopheadzRESTController {

    val counter = AtomicLong()

    @RequestMapping(value = "/malt", method = arrayOf(RequestMethod.GET))
    fun getMalt(): Array<Malt> {
        System.out.println("Malts requested")
        return arrayOf(Malt(
                id = 1,
                name = "Pale ale",
                country = "Belgium",
                color = 8f,
                efficiency = 37f,
                fermentability = 0.85f,
                description = "pale ale malt"
        ),
                Malt(
                        id = 2,
                        name = "Munich",
                        country = "Belgium",
                        color = 25f,
                        efficiency = 37f,
                        fermentability = 0.85f,
                        description = "Munich malt"
                )
        )
    }
}