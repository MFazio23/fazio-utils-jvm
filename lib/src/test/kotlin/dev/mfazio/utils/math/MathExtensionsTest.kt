package dev.mfazio.utils.math

import org.junit.jupiter.api.Test
import kotlin.test.*

class MathExtensionsTest {

    @Test
    fun `Test products of integers`() {
        assertEquals(6, listOf(2, 3).product())
        assertEquals(16, listOf(2, 8).product())
        assertEquals(24, listOf(2, 3, 4).product())
        assertEquals(70, listOf(2, 5, 7).product())
        assertEquals(120, listOf(2, 3, 4, 5).product())
        assertEquals(720, listOf(2, 3, 4, 5, 6).product())
    }

    @Test
    fun `Test products of longs`() {
        assertEquals(6L, listOf(2L, 3L).product())
        assertEquals(16L, listOf(2L, 8L).product())
        assertEquals(24L, listOf(2L, 3L, 4L).product())
        assertEquals(70L, listOf(2L, 5L, 7L).product())
        assertEquals(120L, listOf(2L, 3L, 4L, 5L).product())
        assertEquals(720L, listOf(2L, 3L, 4L, 5L, 6L).product())
    }

    @Test
    fun `Test median of integers with odd number of elements`() {
        assertEquals(5.0, listOf(1, 5, 9).median())
        assertEquals(5.0, listOf(9, 1, 5).median())
        assertEquals(42.0, listOf(42).median())
        assertEquals(3.0, setOf(5, 3, 1).median())
    }

    @Test
    fun `Test median of integers with even number of elements`() {
        assertEquals(2.5, listOf(1, 2, 3, 4).median())
        assertEquals(2.5, listOf(4, 1, 3, 2).median())
        assertEquals(1.5, listOf(1, 2).median())
        assertEquals(3.5, setOf(1, 3, 4, 6).median())
    }

    @Test
    fun `Test median of empty integer collection returns null`() {
        assertNull(emptyList<Int>().median())
        assertNull(emptySet<Int>().median())
    }

    @Test
    fun `Test median of doubles with odd number of elements`() {
        assertEquals(5.5, listOf(1.1, 5.5, 9.9).median())
        assertEquals(5.5, listOf(9.9, 1.1, 5.5).median())
        assertEquals(3.14, listOf(3.14).median())
    }

    @Test
    fun `Test median of doubles with even number of elements`() {
        assertEquals(2.5, listOf(1.0, 2.0, 3.0, 4.0).median())
        assertEquals(3.25, listOf(1.5, 4.0, 2.5, 6.0).median())
        assertEquals(1.5, listOf(1.0, 2.0).median())
    }

    @Test
    fun `Test median of empty double collection returns null`() {
        assertNull(emptyList<Double>().median())
        assertNull(emptySet<Double>().median())
    }
}