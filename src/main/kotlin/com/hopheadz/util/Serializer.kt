package com.hopheadz.util

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser

/**
 * Created by NS on 08/04/16.
 */
open class Serializer() {
    fun <T>toMap(obj: T, entity: Class<T>) : Map<String, Any> {
        var map : Map<String, Any>? = null
        entity.declaredFields.forEach { field ->
            field.isAccessible = true
            map = map?.plus(field.name to field.get(obj)) ?: mapOf(field.name to field.get(obj))
        }
        return map ?: mapOf()
    }

    fun <T>fromMap(map: Map<String, Any>, entity: Class<T>) : T {
        var obj = entity.newInstance()

        entity.declaredFields.forEach { field ->
            field.isAccessible = true
            when (field.type.name){
                "float" -> field.set(obj, map.get(field.name).toString().toFloat())
                "int" -> field.set(obj, map.get(field.name).toString().toInt())
                else -> field.set(obj, field.type.cast(map.get(field.name)))
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
}