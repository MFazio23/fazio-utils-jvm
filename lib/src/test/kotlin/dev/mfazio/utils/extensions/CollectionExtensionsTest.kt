package dev.mfazio.utils.extensions

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized
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
    fun `getOrZero on Int Map with value returns proper value`() {
        val validKey = "thisIsValid"

        val testMap = mapOf(
            "invalid" to 3,
            validKey to 2,
            "invalidAlso" to 8,
        )

        assertEquals(2, testMap.getOrZero(validKey))
    }

    @Test
    fun `getOrZero on Int Map without value returns zero`() {
        val validKey = "thisIsValid"

        val testMap = mapOf(
            "invalid" to 3,
            "anotherInvalid" to 2,
            "invalidAlso" to 8,
        )

        assertEquals(0, testMap.getOrZero(validKey))
    }

    @Test
    fun `getOrZero on empty Int Map return zero`() {
        val validKey = "thisIsValid"

        val testMap = emptyMap<String, Int>()

        assertEquals(0, testMap.getOrZero(validKey))
    }

    @Test
    fun `getOrZero on Double Map with value returns proper value`() {
        val validKey = "thisIsValid"

        val testMap = mapOf(
            "invalid" to 3.2,
            validKey to 2.3,
            "invalidAlso" to 8.15,
        )

        assertEquals(2.3, testMap.getOrZero(validKey))
    }

    @Test
    fun `getOrZero on Double Map without value returns zero`() {
        val validKey = "thisIsValid"

        val testMap = mapOf(
            "invalid" to 3.2,
            "anotherInvalid" to 2.3,
            "invalidAlso" to 8.15,
        )

        assertEquals(0.0, testMap.getOrZero(validKey))
    }

    @Test
    fun `getOrZero on empty Double Map return zero`() {
        val validKey = "thisIsValid"

        val testMap = emptyMap<String, Double>()

        assertEquals(0.0, testMap.getOrZero(validKey))
    }

    @Test
    fun `getOrIntZero on Double Map with value returns proper value`() {
        val validKey = "thisIsValid"

        val testMap = mapOf(
            "invalid" to 3.2,
            validKey to 2.0,
            "invalidAlso" to 8.15,
        )

        assertEquals(2, testMap.getOrIntZero(validKey))
    }

    @Test
    fun `getOrIntZero on Double Map without value returns zero`() {
        val validKey = "thisIsValid"

        val testMap = mapOf(
            "invalid" to 3.2,
            "anotherInvalid" to 2.3,
            "invalidAlso" to 8.15,
        )

        assertEquals(0, testMap.getOrIntZero(validKey))
    }

    @Test
    fun `getOrIntZero on empty Double Map returns zero`() {
        val validKey = "thisIsValid"

        val testMap = emptyMap<String, Double>()

        assertEquals(0, testMap.getOrIntZero(validKey))
    }

    @Test
    fun `getOrDefault, list of Int, valid index returns value`() {
        val testList = listOf(3, 6, 9, 1, 4)

        assertEquals(9, testList.getOrDefault(2, 23))
    }

    @Test
    fun `getOrDefault, list of Int, invalid index returns default`() {
        val testList = listOf(3, 6, 9, 1, 4)

        assertEquals(23, testList.getOrDefault(7, 23))
    }

    @Test
    fun `getOrDefault, empty list of Int returns default`() {
        val testList = emptyList<Int>()

        assertEquals(23, testList.getOrDefault(2, 23))
    }

    @Test
    fun `getOrDefault, list of Double, valid index returns value`() {
        val testList = listOf(3.0, 7.0, 1.0, 9.0, 4.0)

        assertEquals(7.0, testList.getOrDefault(1, 23.0))
    }

    @Test
    fun `getOrDefault, list of Double, invalid index returns double default`() {
        val testList = listOf(3.0, 7.0, 1.0, 9.0, 4.0)

        assertEquals(23.0, testList.getOrDefault(31, 23.0))
    }

    @Test
    fun `getOrDefault, list of Double, invalid index returns Int default`() {
        val testList = listOf(3.0, 7.0, 1.0, 9.0, 4.0)

        assertEquals(23, testList.getOrDefault(31, 23))
    }

    private data class PrintTestItem(
        val value: String
    ) {
        override fun toString(): String = "PTI=$value"
    }
}