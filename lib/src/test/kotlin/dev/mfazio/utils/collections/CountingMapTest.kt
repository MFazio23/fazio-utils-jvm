package dev.mfazio.utils.collections

import kotlin.test.*

class CountingMapTest {

    @Test
    fun `default constructor creates an empty map`() {
        val countingMap = CountingMap<String>()

        assertTrue(countingMap.isEmpty())
        assertEquals(0, countingMap.size)
        assertEquals(0, countingMap["nonExistent"])
        assertEquals(0, countingMap.getCount("nonExistent"))
    }

    @Test
    fun `fromList counts occurrences of items`() {
        val items = listOf("apple", "banana", "apple", "cherry", "apple", "banana")
        val countingMap = CountingMap.fromList(items)

        assertEquals(3, countingMap["apple"])
        assertEquals(2, countingMap["banana"])
        assertEquals(1, countingMap["cherry"])
        assertEquals(0, countingMap["orange"])
    }

    @Test
    fun `fromMap initializes from existing map`() {
        val initialMap = mapOf("A" to 5, "B" to 10)
        val countingMap = CountingMap.fromMap(initialMap)

        assertEquals(5, countingMap["A"])
        assertEquals(10, countingMap["B"])
        assertEquals(2, countingMap.size)
    }

    @Test
    fun `countsOf counts occurrences from any iterable`() {
        val set = setOf(1, 2, 3)
        val countingMap = CountingMap.countsOf(set)

        assertEquals(1, countingMap[1])
        assertEquals(1, countingMap[2])
        assertEquals(1, countingMap[3])
    }

    @Test
    fun `toCountingMap extension function creates a CountingMap with counted occurrences`() {
        val list = listOf("x", "y", "x", "z", "x")
        val countingMap = list.toCountingMap()

        assertEquals(3, countingMap["x"])
        assertEquals(1, countingMap["y"])
        assertEquals(1, countingMap["z"])
    }

    @Test
    fun `countingMapOf with vararg keys counts occurrences`() {
        val countingMap = countingMapOf("foo", "bar", "foo", "baz", "foo")

        assertEquals(3, countingMap["foo"])
        assertEquals(1, countingMap["bar"])
        assertEquals(1, countingMap["baz"])
        assertEquals(0, countingMap["unknown"])
    }

    @Test
    fun `countingMapOf with vararg pairs initializes counts`() {
        val countingMap = countingMapOf("alpha" to 2, "beta" to 7)

        assertEquals(2, countingMap["alpha"])
        assertEquals(7, countingMap["beta"])
        assertEquals(2, countingMap.size)
    }

    @Test
    fun `increment adds 1 by default and returns new count`() {
        val countingMap = CountingMap<String>()

        val first = countingMap.increment("key")
        assertEquals(1, first)
        assertEquals(1, countingMap["key"])

        val second = countingMap.increment("key")
        assertEquals(2, second)
        assertEquals(2, countingMap["key"])
    }

    @Test
    fun `increment with custom amount adds specified amount`() {
        val countingMap = CountingMap<String>()

        val result = countingMap.increment("key", 5)
        assertEquals(5, result)
        assertEquals(5, countingMap["key"])

        val next = countingMap.increment("key", 3)
        assertEquals(8, next)
        assertEquals(8, countingMap["key"])
    }

    @Test
    fun `decrement subtracts 1 by default and returns new count`() {
        val countingMap = countingMapOf("key" to 5)

        val first = countingMap.decrement("key")
        assertEquals(4, first)
        assertEquals(4, countingMap["key"])

        val second = countingMap.decrement("key")
        assertEquals(3, second)
        assertEquals(3, countingMap["key"])
    }

    @Test
    fun `decrement on absent key goes negative`() {
        val countingMap = CountingMap<String>()

        val result = countingMap.decrement("key")
        assertEquals(-1, result)
        assertEquals(-1, countingMap["key"])
    }

    @Test
    fun `decrement with custom amount subtracts specified amount`() {
        val countingMap = countingMapOf("key" to 10)

        val result = countingMap.decrement("key", 4)
        assertEquals(6, result)
        assertEquals(6, countingMap["key"])
    }

    @Test
    fun `plusAssign with key increments by 1`() {
        val countingMap = CountingMap<String>()

        countingMap += "item"
        countingMap += "item"

        assertEquals(2, countingMap["item"])
    }

    @Test
    fun `plusAssign with pair increments by specified amount`() {
        val countingMap = CountingMap<String>()

        countingMap += "item" to 5
        countingMap += "item" to 3

        assertEquals(8, countingMap["item"])
    }

    @Test
    fun `plusAssign with another map adds all counts`() {
        val countingMap = countingMapOf("a" to 2, "b" to 3)
        val otherMap = mapOf("b" to 4, "c" to 6)

        countingMap += otherMap

        assertEquals(2, countingMap["a"])
        assertEquals(7, countingMap["b"])
        assertEquals(6, countingMap["c"])
    }

    @Test
    fun `minusAssign with key decrements by 1`() {
        val countingMap = countingMapOf("item" to 5)

        countingMap -= "item"

        assertEquals(4, countingMap["item"])
    }

    @Test
    fun `minusAssign with pair decrements by specified amount`() {
        val countingMap = countingMapOf("item" to 10)

        countingMap -= "item" to 4

        assertEquals(6, countingMap["item"])
    }

    @Test
    fun `minusAssign with another map subtracts all counts`() {
        val countingMap = countingMapOf("a" to 5, "b" to 8)
        val otherMap = mapOf("a" to 2, "b" to 3, "c" to 1)

        countingMap -= otherMap

        assertEquals(3, countingMap["a"])
        assertEquals(5, countingMap["b"])
        assertEquals(-1, countingMap["c"])
    }

    @Test
    fun `indexed get returns count for present keys`() {
        val countingMap = countingMapOf("key" to 7)

        assertEquals(7, countingMap["key"])
    }

    @Test
    fun `indexed get returns 0 for absent keys`() {
        val countingMap = CountingMap<String>()

        assertEquals(0, countingMap["missing"])
        assertFalse(countingMap.containsKey("missing"))
    }

    @Test
    fun `getCount returns count for present keys`() {
        val countingMap = countingMapOf("key" to 5)

        assertEquals(5, countingMap.getCount("key"))
    }

    @Test
    fun `getCount returns 0 for absent keys`() {
        val countingMap = CountingMap<String>()

        assertEquals(0, countingMap.getCount("missing"))
    }

    @Test
    fun `indexed set updates key count directly`() {
        val countingMap = CountingMap<String>()

        countingMap["key"] = 42

        assertEquals(42, countingMap["key"])
        assertEquals(1, countingMap.size)
        assertTrue(countingMap.containsKey("key"))
    }

    @Test
    fun `map delegation exposes keys values and entries`() {
        val countingMap = countingMapOf("x" to 1, "y" to 2)

        assertEquals(setOf("x", "y"), countingMap.keys)
        assertTrue(countingMap.values.containsAll(listOf(1, 2)))
        assertEquals(2, countingMap.entries.size)
        assertTrue(countingMap.containsKey("x"))
        assertFalse(countingMap.containsKey("z"))
    }

    @Test
    fun `equals and hashCode match identical CountingMap or Map`() {
        val map1 = countingMapOf("a" to 1, "b" to 2)
        val map2 = countingMapOf("a" to 1, "b" to 2)
        val map3 = countingMapOf("a" to 1, "b" to 3)

        assertEquals(map1, map2)
        assertEquals(map1.hashCode(), map2.hashCode())
        assertNotEquals(map1, map3)
    }

    @Test
    fun `toString delegates to internal map toString`() {
        val countingMap = countingMapOf("test" to 3)

        assertEquals("{test=3}", countingMap.toString())
    }
}