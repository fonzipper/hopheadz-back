package com.hopheadz.util

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.int
import com.beust.klaxon.string
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.joda.time.DateTime

/**
 * Created by NS on 08/04/16
 */

open class Serializer() {
    fun <T>toMap(obj: T, entity: Class<T>) : Map<String, Any> {
        var map : Map<String, Any>? = null
        entity.declaredFields.forEach { field ->
            field.isAccessible = true
            when (field.type.name){
                "org.joda.time.DateTime" -> map = map?.plus(field.name to field.get(obj).toString()) ?: mapOf(field.name to field.get(obj).toString())
                else -> map = map?.plus(field.name to field.get(obj)) ?: mapOf(field.name to field.get(obj))
            }

        }
        return map ?: mapOf()
    }

    fun <T>fromMap(map: Map<String, Any>, entity: Class<T>) : T {
        val obj = entity.newInstance()

        entity.declaredFields.forEach { field ->
            field.isAccessible = true
            when (field.type.name){
                "float" -> field.set(obj, map[field.name].toString().toFloat())
                "int" -> field.set(obj, map[field.name].toString().toInt())
                "boolean" -> field.set(obj, map[field.name].toString() == "true")
                "org.joda.time.DateTime" -> field.set(obj, DateTime.parse(map[field.name].toString()))
                else -> field.set(obj, field.type.cast(map[field.name]))
            }
        }

        return obj
    }

    //csv file must contain headers
    fun <T> parseCSV(csv: String, entity: Class<T>): List<T> {
        val parser = CSVParser.parse(csv, CSVFormat.DEFAULT.withHeader())
        var result = listOf<T>()

        for (row in parser) {
            result = result.plus(this.fromMap(row.toMap(), entity))
        }
        return result
    }

    fun <T> parseJsonObject(json: String, entity: Class<T>) : T {
        val parser = Parser().parse(StringBuilder(json)) as JsonObject
        val obj = entity.newInstance()

        entity.declaredFields.forEach { field ->
            field.isAccessible = true
            when (field.type.name){
                "float" -> {
                    val value = parser.int(field.name)
                    field.set(obj, value?.toFloat())
                }
                "int" -> field.set(obj, parser.int(field.name))
                "org.joda.time.DateTime" -> field.set(obj, DateTime.parse(parser.string(field.name)))
                else -> field.set(obj, field.type.cast(parser.string(field.name)))
            }
        }

        return obj
    }
}