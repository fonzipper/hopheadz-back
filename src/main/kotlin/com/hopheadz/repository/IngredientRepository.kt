package com.hopheadz.repository

import com.hopheadz.data.*
import com.hopheadz.util.Serializer
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import org.bson.Document
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by NS on 08/04/16
 */

//@Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
open class IngredientRepository @Autowired constructor(val db: MongoDatabase, val serializer: Serializer) {

    fun findAllMalts() : Array<Malt> {
        var res = listOf<Malt>()
        db.getCollection("malts").find().distinct()
                .forEach { it ->
                    res = res.plus(serializer.fromMap(it.toSortedMap(), Malt::class.java))
                }
        return res.toTypedArray()
    }

    fun uploadMalts(csv: String){
        val uploadArray = serializer.parseCSV(csv, Malt::class.java)

        if (uploadArray.isNotEmpty())
            uploadArray
                    .filter { it.efficiency != 0 }
                    .forEach {
                        db.getCollection("malts")
                                .insertOne(Document(serializer.toMap(it, Malt::class.java)))
                    }
    }

    fun findAllHops() : Array<Hop> {
        var res = listOf<Hop>()
        db.getCollection("hops").find().distinct()
                .forEach { it ->
                    res = res.plus(serializer.fromMap(it.toSortedMap(), Hop::class.java))
                }
        return res.toTypedArray()
    }

    fun uploadHops(csv: String) {
        val uploadArray = serializer.parseCSV(csv, Hop::class.java)

        if (uploadArray.isNotEmpty())
            uploadArray
                    .filter { it.alpha != 0f }
                    .forEach {
                        db.getCollection("hops")
                                .insertOne(Document(serializer.toMap(it, Hop::class.java)))
                    }
    }

    fun findAllYeasts() : Array<Yeast> {
        var res = listOf<Yeast>()
        db.getCollection("yeasts").find().distinct()
                .forEach { it ->
                    res = res.plus(serializer.fromMap(it.toSortedMap(), Yeast::class.java))
                }
        return res.toTypedArray()
    }

    fun uploadYeasts(csv: String) {
        val uploadArray = serializer.parseCSV(csv, Yeast::class.java)

        if (uploadArray.isNotEmpty())
            uploadArray
                    .filter { it.name != "" }
                    .forEach {
                        db.getCollection("yeasts")
                                .insertOne(Document(serializer.toMap(it, Yeast::class.java)))
                    }
    }

    fun findAllStyles() : Array<Style> {
        var res = listOf<Style>()
        db.getCollection("styles").find().distinct()
                .forEach { it ->
                    res = res.plus(serializer.fromMap(it.toSortedMap(), Style::class.java))
                }
        return res.toTypedArray()
    }

    fun uploadStyles(csv: String) {
        val uploadArray = serializer.parseCSV(csv, Style::class.java)

        if (uploadArray.isNotEmpty())
            uploadArray
                    .filter { it.name != "" }
                    .forEach {
                        it.minColor = Math.round(it.minColor * 1.97f).toFloat()
                        it.maxColor = Math.round(it.maxColor * 1.97f).toFloat()
                        it.minGravity = (it.minGravity - 1000) / 4
                        it.maxGravity = (it.maxGravity - 1000) / 4
                        db.getCollection("styles")
                                .insertOne(Document(serializer.toMap(it, Style::class.java)))
                    }
    }

    fun findAllRecipes(id: String?, userId: String?) : Array<String> {
        var res =  listOf<String>()
        val ndoc = Document()
        if (id != null) ndoc.append("_id", ObjectId(id))
        if (userId != null) ndoc.append("owner", userId)

        db.getCollection("recipes").find(ndoc).distinct()
                .forEach { it ->
                    res = res.plus(it.toJson())
                }

        return res.toTypedArray()
    }

    fun insertRecipe(recipe: String) : String {
        val rcp = Document.parse(recipe)
        val id: Any? = rcp["_id"]
        if (id != null && id != "undefined") {
            val lookup = "{\"_id\" : { \"\$oid\" : \"$id\"}}"
            val doc = Document.parse(lookup)
            val opts = UpdateOptions()
            opts.upsert(true)
            db.getCollection("recipes").replaceOne(doc, rcp, opts)
        } else {
            db.getCollection("recipes").insertOne(rcp)
        }
        return rcp.toJson()
    }

    fun getBrewSetup(userId : String) : BrewSetup? {
        val ndoc = Document()
        ndoc.append("owner", userId)

        var res: BrewSetup? = null

        db.getCollection("brew-setups").find(ndoc).distinct()
                .forEach { it ->
                    res = serializer.fromMap(it.toSortedMap(), BrewSetup::class.java)
                }
        return res
    }

    fun saveBrewSetup(brewSetup: String) : String {
        val stp = Document.parse(brewSetup)
        val id: Any? = stp["_id"]
        val ndoc = Document()
        if (id != null && id != "undefined") {
            ndoc.append("_id", ObjectId(id.toString()))
            val opts = UpdateOptions()
            opts.upsert(true)
            db.getCollection("brew-setups").replaceOne(ndoc, stp, opts)
        } else {
            db.getCollection("brew-setups").insertOne(stp)
        }
        return stp.toJson()
    }

    fun uploadMashSteps(csv: String) {
        val uploadArray = serializer.parseCSV(csv, MashStep::class.java)

        if (uploadArray.isNotEmpty())
            uploadArray
                    .filter { it.name != "" }
                    .forEach {
                        db.getCollection("mash-steps")
                                .insertOne(Document(serializer.toMap(it, MashStep::class.java)))
                    }
    }

    fun uploadMashTypes(csv: String) {
        val uploadArray = serializer.parseCSV(csv, MashType::class.java)

        if (uploadArray.isNotEmpty())
            uploadArray
                    .filter { it.name != "" }
                    .forEach {
                        db.getCollection("mash-types")
                                .insertOne(Document(serializer.toMap(it, MashType::class.java)))
                    }
    }

    fun findAllMashTypes() : Array<MashType> {
        var res = listOf<MashType>()
        var steps = mapOf<String, MashStep>()
        db.getCollection("mash-steps").find().distinct()
                .forEach { it ->
                    val ms = serializer.fromMap(it.toSortedMap(), MashStep::class.java)
                    steps = steps.plus(pair = Pair(ms.name, ms))
                }

        db.getCollection("mash-types").find().distinct()
                .forEach { it ->
                    res = res.plus(serializer.fromMap(it.toSortedMap(), MashType::class.java))
                }

        for (mt in res) {
            mt.mashSteps = arrayOf()
            val stepSeq = mt.mashSequence.split("%%")
            var stepNumber = 1
            for (st in stepSeq) {
                val step: StepDescription = serializer.parseJsonObject(st, StepDescription::class.java)
                val ms = steps[step.name]?.clone()
                if (ms != null) {
                    ms.stepNumber = stepNumber
                    ms.stepTemperature = step.temperature
                    ms.time = step.time
                    ms.ratio = step.ratio
                    mt.mashSteps = mt.mashSteps.plus(ms)
                    stepNumber++
                }
            }
        }

        return res.toTypedArray()
    }

    fun findAllMisc() : Array<Misc> {
        var res = listOf<Misc>()

        db.getCollection("misc").find().distinct()
                .forEach { it ->
                    res = res.plus(serializer.fromMap(it.toSortedMap(), Misc::class.java))
                }

        return res.toTypedArray()
    }

    fun uploadMiscs(csv: String) {
        val uploadArray = serializer.parseCSV(csv, Misc::class.java)

        if (uploadArray.isNotEmpty())
            uploadArray
                    .filter { it.name != "" }
                    .forEach {
                        db.getCollection("misc")
                                .insertOne(Document(serializer.toMap(it, Misc::class.java)))
                    }
    }
}