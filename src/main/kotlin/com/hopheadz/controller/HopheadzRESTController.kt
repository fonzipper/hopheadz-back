package com.hopheadz.controller

import com.hopheadz.data.Hop
import com.hopheadz.data.Malt
import com.hopheadz.data.Style
import com.hopheadz.data.Yeast
import com.hopheadz.repository.IngredientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by NS on 30/03/16.
 */
@CrossOrigin(origins = arrayOf("*"))
@RestController
class HopheadzRESTController @Autowired constructor(val iRepo: IngredientRepository) {

    @RequestMapping(value = "/malt", method = arrayOf(RequestMethod.GET))
    fun getMalt(): Array<Malt> {
        return iRepo.findAllMalts()
    }

    @RequestMapping(value = "/malt", method = arrayOf(RequestMethod.POST), consumes = arrayOf("*/*"))
    fun uploadMalt(@RequestBody malts: String) {
        iRepo.uploadMalts(malts)
    }

    @RequestMapping(value = "/hop", method = arrayOf(RequestMethod.GET))
    fun getHop(): Array<Hop> {
        return iRepo.findAllHops()
    }

    @RequestMapping(value = "/hop", method = arrayOf(RequestMethod.POST), consumes = arrayOf("text/csv"))
    fun uploadHop(@RequestBody hops: String) {
        return iRepo.uploadHops(hops)
    }

    @RequestMapping(value = "/yeast", method = arrayOf(RequestMethod.GET))
    fun getYeast(): Array<Yeast> {
        return iRepo.findAllYeasts()
    }

    @RequestMapping(value = "/yeast", method = arrayOf(RequestMethod.POST), consumes = arrayOf("text/csv"))
    fun uploadYeast(@RequestBody yeasts: String) {
        return iRepo.uploadYeasts(yeasts)
    }

    @RequestMapping(value = "/style", method = arrayOf(RequestMethod.GET))
    fun getStyle(): Array<Style> {
        return iRepo.findAllStyles()
    }

    @RequestMapping(value = "/style", method = arrayOf(RequestMethod.POST), consumes = arrayOf("text/csv"))
    fun uploadStyle(@RequestBody yeast: String) {
        return iRepo.uploadStyles(yeast)
    }
}