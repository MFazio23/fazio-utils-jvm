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

    private data class PrintTestItem(
        val value: String
    ) {
        override fun toString(): String = "PTI=$value"
    }
}