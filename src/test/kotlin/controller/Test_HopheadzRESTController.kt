package controller

import com.github.fakemongo.Fongo
import com.hopheadz.controller.HopheadzRESTController
import com.hopheadz.repository.IngredientRepository
import com.hopheadz.util.Serializer
import org.joda.time.DateTime
import org.junit.Test
import org.junit.Assert.*

/**
 * Created by capitan on 8/18/16.
 */
class Test_HopheadzRESTController {
    companion object {
        val db = Fongo("fakedatabase").getDatabase("itsafake")
        val serializer = Serializer()
        val repo = IngredientRepository(db, serializer)
        val controller = HopheadzRESTController(repo)
    }

    @Test fun testMalt(){
        val csvString = "name,country,color,efficiency,fermentability,description\r\nPale Ale,Belgium,8,37,0.85,pale ale malt"

        controller.uploadMalt(csvString)

        val malts = controller.getMalt()

        assertEquals("Controller returned invalid malt quantity", 1, malts.size)
        assertEquals("Invalid field mapping", "Pale Ale", malts[0].name)
        assertEquals("Invalid type conversion", 0.85f, malts[0].fermentability)
    }

    @Test fun testHop(){
        val csvString = "name,country,alpha,description\r\nMosaic,USA,15.2,mosaic hops"

        controller.uploadHop(csvString)

        val hops = controller.getHop()

        assertEquals("Controller returned invalid malt quantity", 1, hops.size)
        assertEquals("Invalid field mapping", "Mosaic", hops[0].name)
        assertEquals("Invalid type conversion", 15.2f, hops[0].alpha)
    }

    @Test fun testYeast(){
        val csvString = "name,country,productionDate,isLiquid\r\nNottingham,Austria,2015-12-15,false"

        controller.uploadYeast(csvString)

        val yeasts = controller.getYeast()

        assertEquals("Controller returned invalid malt quantity", 1, yeasts.size)
        assertEquals("Invalid field mapping", "Nottingham", yeasts[0].name)
        assertEquals("Invalid type conversion", false, yeasts[0].isLiquid)
        assertEquals("Invalid date conversion", DateTime.parse("2015-12-15").toString(), yeasts[0].productionDate.toString())
    }

    @Test fun testStyle(){
        val csvString = "name,minGravity,maxGravity,minBitterness,maxBitterness,minColor,maxColor,minABV,maxABV,description\r\nIndia Pale Ale,1.056,1.064,50,70,23,26,4.7,5.5,India pale ale"

        controller.uploadStyle(csvString)

        val styles = controller.getStyle()

        assertEquals("Controller returned invalid malt quantity", 1, styles.size)
        assertEquals("Invalid field mapping", "India Pale Ale", styles[0].name)
        assertEquals("Invalid type conversion", 1.064f, styles[0].maxGravity)
    }
}