package util

import com.hopheadz.data.Malt
import com.hopheadz.util.Serializer
import org.junit.Test
import org.junit.Assert.*

/**
 * Created by capitan on 8/18/16.
 */
class Test_Serializer {

    val serializer = Serializer()

    @Test
    fun testToMap() {

        val malt = Malt(
                name = "test malt",
                country = "Belgium",
                color = 10f,
                efficiency = 31,
                fermentability = 40f,
                description = "desc"
        )

        val result = serializer.toMap(malt, Malt::class.java)

        assertEquals("Not all fields were mapped", 6, result.size)
        assertEquals("Fields mapped wrong", "Belgium", result["country"])
        assertEquals("Fields mapped wrong", 40f, result["fermentability"])
    }

//    @Test(dataProvider = "name")
//    fun params(foo: String, bar: Malt, message: String) {
//        assertEquals()
//
//    }
//
//    @DataProvider
//    fun dataForTest(): Array<Array<Any?>> = arrayOf(
//            arrayOf("foo", Malt(name = "mop"), "Malt with name"))

    @Test
    fun testFromMap() {
        val maltMap = mapOf(
                "name" to "test malt",
                "country" to "Belgium",
                "color" to 10f,
                "efficiency" to 31,
                "fermentability" to 40f,
                "description" to "desc"
        )

        val result = serializer.fromMap(maltMap, Malt::class.java)

        assertEquals("Result is wrong type", Malt::class.java, result.javaClass)
        assertEquals("Fields mapped wrong", "test malt", result.name)
        assertEquals("Fields mapped wrong", 31, result.efficiency)
    }

    @Test
    fun testParseCSV(){
        val csvString = "name,country,color,efficiency,fermentability,description\r\nPale Ale,Belgium,8,37,0.85,pale ale malt"

        val result = serializer.parseCSV(csvString, Malt::class.java)

        assertEquals("Result array has wrong size", 1, result.size)
        assertEquals("Result array has wrong type", Malt::class.java, result[0].javaClass)
        assertEquals("Fields mapped wrong", "Pale Ale", result[0].name)
    }

}