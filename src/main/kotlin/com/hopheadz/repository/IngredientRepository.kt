package com.hopheadz.repository

import com.hopheadz.data.Hop
import com.hopheadz.data.Malt
import com.hopheadz.data.Style
import com.hopheadz.data.Yeast
import com.hopheadz.util.Serializer
import com.mongodb.client.MongoDatabase
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by NS on 08/04/16.
 */

open class IngredientRepository @Autowired constructor(var db: MongoDatabase, val serializer: Serializer) {
    fun findAllMalts() : Array<Malt> {
        var res = listOf<Malt>()
        db.getCollection("malts").find().distinct()
                .forEach { it ->
                    res = res.plus(serializer.fromMap(it.toSortedMap(), Malt::class.java))
                }
        return res.toTypedArray()
    }

    fun updloadMalts(csv: String) {
        var uploadArray = serializer.parseCSV<Malt>(csv, Malt::class.java)

        if (uploadArray.size > 0)
            for (mmt in uploadArray)
                if (mmt.efficiency != 0) db.getCollection("malts")
                        .insertOne(Document(serializer.toMap(mmt, Malt::class.java)))
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
        var uploadArray = serializer.parseCSV<Hop>(csv, Hop::class.java)

        if (uploadArray.size > 0)
            for (hp in uploadArray)
                if (hp.alpha != 0f) db.getCollection("hops")
                        .insertOne(Document(serializer.toMap(hp, Hop::class.java)))
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
        var uploadArray = serializer.parseCSV<Yeast>(csv, Yeast::class.java)

        if (uploadArray.size > 0)
            for (yt in uploadArray)
                if (yt.name != "") db.getCollection("yeasts")
                        .insertOne(Document(serializer.toMap(yt, Yeast::class.java)))
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
        var uploadArray = serializer.parseCSV<Style>(csv, Style::class.java)

        if (uploadArray.size > 0)
            for (st in uploadArray)
                if (st.name != "") db.getCollection("styles")
                        .insertOne(Document(serializer.toMap(st, Style::class.java)))
    }
}