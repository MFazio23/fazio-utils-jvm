package dev.mfazio.utils.extensions

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized
import dev.mfazio.utils.random.IntListRandom
import kotlin.random.Random
import kotlin.test.*

class CollectionExtensionsTest {
    @Test
    fun `printEach prints each string value`() {
        val items = listOf("This", "Should", "All", "Print")

        val result = tapSystemOutNormalized {
            items.printEach()
        }.trim()

        assertEquals(items.joinToString("\n"), result)
    }

    @Test
    fun `printEach prints each toString() value on objects`() {
        val items = listOf(
            PrintTestItem("This"),
            PrintTestItem("also"),
            PrintTestItem("should"),
            PrintTestItem("print"),
            PrintTestItem("each"),
            PrintTestItem("item"),
        )

        val result = tapSystemOutNormalized {
            items.printEach()
        }.trim()

        assertEquals(items.joinToString("\n"), result)
    }

    @Test
    fun `filterNotNullValues removes entries with a null value`() {
        val includedKey = "included"
        val excludedKey = "excluded"

        val map = mapOf(
            includedKey to 23,
            excludedKey to null,
            "otherKey" to 12,
            "yetAnotherKey" to 5,
            "oneMoreKey" to 33,
            "anotherNullKey" to null,
        )

        val filteredMap = map.filterNotNullValues()

        assertEquals(4, filteredMap.size)
        assertTrue(filteredMap.containsKey(includedKey))
        assertFalse(filteredMap.containsKey(excludedKey))
    }

    @Test
    fun `filterNotNullValues returns the same Map when no items have a null value`() {
        val includedKey = "included"

        val map = mapOf(
            includedKey to 23,
            "otherKey" to 12,
            "yetAnotherKey" to 5,
            "oneMoreKey" to 33,
            "jklolExtraKey" to 100,
        )

        val filteredMap = map.filterNotNullValues()

        assertEquals(5, filteredMap.size)
        assertTrue(filteredMap.containsKey(includedKey))
    }

    @Test
    fun `filterNotNullValues works on an empty Map`() {
        val filteredMap = emptyMap<String, Int?>().filterNotNullValues()

        assertTrue(filteredMap.isEmpty())
    }

    @Test
    fun `randomOrNull returns a valid value`() {
        val testData = listOf(
            846, 68466, 247, 381, 3, 484, 11, 180
        )

        assertTrue(testData.contains(testData.randomOrNull()))
    }

    @Test
    fun `randomOrNull returns the correct value`() {
        val testData = listOf(
            846, 68466, 247, 381, 3, 484, 11, 180
        )

        val testRandom = IntListRandom(
            listOf(
                3, 3, 7, 1, 3, 5, 0, 1, 5
            )
        )

        assertEquals(3, testData.randomOrNull(testRandom))
    }

    @Test
    fun `randomOrNull returns null from an empty list`() {
        val testData = emptyList<Int>()

        assertNull(testData.randomOrNull())
        assertNull(testData.randomOrNull(Random.Default))
    }

    @Test
    fun `randomOrNull returns null from a null list`() {
        val testData: List<Int>? = null

        assertNull(testData.randomOrNull())
        assertNull(testData.randomOrNull(Random.Default))
    }

    private data class PrintTestItem(
        val value: String
    ) {
        override fun toString(): String = "PTI=$value"
    }
}