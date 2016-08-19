package data

import com.hopheadz.data.*
import org.joda.time.DateTime
import org.junit.Test
import org.junit.Assert.*

/**
 * Created by capitan on 8/18/16.
 */
class Test_DataClasses {
    @Test
    fun testCreateHop(){
        val hop = Hop()

        assertEquals(hop.alpha, 0f)
        assertEquals(hop.name, "")
        assertEquals(hop.country, "")
        assertEquals(hop.description, "")
    }

    @Test
    fun testCreateMalt(){
        val malt = Malt()

        assertEquals(malt.color, 0f)
        assertEquals(malt.efficiency, 0)
        assertEquals(malt.fermentability, 0f)
        assertEquals(malt.name, "")
        assertEquals(malt.country, "")
        assertEquals(malt.description, "")
    }

    @Test
    fun testCreateYeast(){
        val yeast = Yeast()

        assertEquals(yeast.isLiquid, false)
        assertEquals(yeast.name, "")
        assertEquals(yeast.country, "")
        assertEquals(yeast.productionDate.toDate().toString(), DateTime.now().toDate().toString())
    }

    @Test
    fun testCreateStyle(){
        val style = Style()

        assertEquals(style.maxABV, 0f)
        assertEquals(style.minABV, 0f)
        assertEquals(style.maxBitterness, 0f)
        assertEquals(style.minBitterness, 0f)
        assertEquals(style.maxColor, 0f)
        assertEquals(style.minColor, 0f)
        assertEquals(style.maxGravity, 0f)
        assertEquals(style.minGravity, 0f)
        assertEquals(style.name, "")
        assertEquals(style.description, "")
    }

    @Test
    fun testCreateMashType(){
        val mashType = MashType()

        assertEquals(mashType.name, "")
    }

    @Test
    fun testCreateRecipe(){
        val recipe = Recipe()

        assertEquals(recipe.style, null)
        assertEquals(recipe.malts, null)
        assertEquals(recipe.hops, null)
        assertEquals(recipe.yeasts, null)
        assertEquals(recipe.abv, 0f)
        assertEquals(recipe.bitterness, 0f)
        assertEquals(recipe.color, 0f)
        assertEquals(recipe.gravity, 0f)
        assertEquals(recipe.efficiency, 0f)
        assertEquals(recipe.volume, 0f)
        assertEquals(recipe.authorID, "")
        assertEquals(recipe.created.toDate().toString(), DateTime.now().toDate().toString())
        assertEquals(recipe.isPublic, true)
    }
}