/**
 * Created by NS on 30/03/16.
 */
package com.hopheadz.controller

import com.hopheadz.data.*
import com.hopheadz.repository.IngredientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.net.URLDecoder

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
    fun uploadStyle(@RequestBody styles: String) {
        return iRepo.uploadStyles(styles)
    }

    @RequestMapping(value = "/recipe", method = arrayOf(RequestMethod.GET))
    fun getRecipe(@RequestParam(required = false) id: String?,
                  @RequestParam(required = false) userId: String?)
            : Array<String> {
        var userIdDec = ""
        if (userId != null) {
            userIdDec = URLDecoder.decode(userId, "UTF-8")
        }
        return iRepo.findAllRecipes(id, userIdDec)
    }

    @RequestMapping(value = "/recipe", method = arrayOf(RequestMethod.POST), consumes = arrayOf("text/json"))
    fun uploadRecipe(@RequestBody recipe: String) : String {
        return iRepo.insertRecipe(recipe)
    }

    @RequestMapping(value = "/brew-setup", method = arrayOf(RequestMethod.GET))
    fun getBrewSetup(@RequestParam(required = true) userId: String) : BrewSetup? {
        val userIdDec = URLDecoder.decode(userId, "UTF-8")
        return iRepo.getBrewSetup(userIdDec)
    }

    @RequestMapping(value = "/brew-setup", method = arrayOf(RequestMethod.POST))
    fun saveBrewSetup(@RequestBody setup: String) : String {
        return iRepo.saveBrewSetup(setup)
    }

    @RequestMapping(value = "/mash-type", method = arrayOf(RequestMethod.GET))
    fun getMashTypes() : Array<MashType> {
        return iRepo.findAllMashTypes()
    }

    @RequestMapping(value = "/mash-type", method = arrayOf(RequestMethod.POST), consumes = arrayOf("text/csv"))
    fun uploadMashTypes(@RequestBody mashTypes: String) {
        return iRepo.uploadMashTypes(mashTypes)
    }

    @RequestMapping(value = "/mash-step", method = arrayOf(RequestMethod.POST), consumes = arrayOf("text/csv"))
    fun uploadMashSteps(@RequestBody mashSteps: String) {
        return iRepo.uploadMashSteps(mashSteps)
    }
}