package dev.mfazio.utils.extensions

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NumberExtensionsTest {
    @Test
    fun `Even integers return true from isEven()`() {
        val evenInts = listOf(2, 8, 44, 104, 86, -4, 1000, 16, -86)

        evenInts.forEach { i ->
            assertTrue(i.isEven(), "$i is even, but isEven() was false.")
        }
    }

    @Test
    fun `Odd integers return false from isEven()`() {
        val evenInts = listOf(-77, -15, 23, 55, 187, 9, 1, -43)

        evenInts.forEach { i ->
            assertFalse(i.isEven(), "$i is odd, but isEven() was true.")
        }
    }

    @Test
    fun `Odd integers return true from isOdd()`() {
        val evenInts = listOf(-77, -15, 23, 55, 187, 9, 1, -43)

        evenInts.forEach { i ->
            assertTrue(i.isOdd(), "$i is odd, but isOdd() was false.")
        }
    }

    @Test
    fun `Even integers return false from isOdd()`() {
        val evenInts = listOf(2, 8, 44, 104, 86, -4, 1000, 16, -86)

        evenInts.forEach { i ->
            assertFalse(i.isOdd(), "$i is odd, but isOdd() was true.")
        }
    }

    @Test
    fun `Safe divide returns zero when divisor is zero`() {
        assertEquals(0.0, 24.0.safeDivide(0.0))

        assertEquals(0.0, 24F.safeDivide(0F))

        assertEquals(0.0, 24.safeDivide(0))
    }

    @Test
    fun `Safe divide returns the correct value when divisor is not zero`() {
        assertEquals(3.0, 24.0.safeDivide(8.0))
        assertEquals(3.5, 21.0.safeDivide(6.0))
        assertEquals(2.75, 10.3125.safeDivide(3.75))

        assertEquals(3.0, 24F.safeDivide(8F))
        assertEquals(3.5, 21F.safeDivide(6F))

        assertEquals(3.0, 24.safeDivide(8))
        assertEquals(3.5, 21.safeDivide(6))
    }

    @Test
    fun `A whole number formats to two decimals with toTwoDigits()`() {
        assertEquals("5.00", 5.0.toTwoDigits())
    }

    @Test
    fun `A two digit whole number formats to two decimals with toTwoDigits()`() {
        assertEquals("12.00", 12.0.toTwoDigits())
    }

    @Test
    fun `A non-integer with one significant digit formats to two decimals with toTwoDigits()`() {
        assertEquals("7.10", 7.1.toTwoDigits())
    }

    @Test
    fun `A non-integer with two significant digits formats to two decimals with toTwoDigits()`() {
        assertEquals("21.12", 21.12.toTwoDigits())
    }

    @Test
    fun `A non-integer with three+ significant digits formats to two decimals with toTwoDigits()`() {
        assertEquals("8.23", 8.234.toTwoDigits())
    }

    @Test
    fun `A non-integer with three+ significant digits rounds and formats to two decimals with toTwoDigits()`() {
        assertEquals("17.46", 17.459989.toTwoDigits())
    }
}