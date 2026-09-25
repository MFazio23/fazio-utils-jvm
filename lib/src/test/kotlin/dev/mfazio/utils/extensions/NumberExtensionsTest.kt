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

    @Test
    fun `An Int orZero() returns the Int if not null`() {
        assertEquals(5, 5.orZero())
    }

    @Test
    fun `An Int orZero() returns zero if null`() {
        val nullInt: Int? = null
        assertEquals(0, nullInt.orZero())
    }

    @Test
    fun `A Double orZero() returns the Double if not null`() {
        assertEquals(10.0, 10.0.orZero())
    }

    @Test
    fun `A Double orZero() returns zero if null`() {
        val nullDouble: Double? = null
        assertEquals(0.0, nullDouble.orZero())
    }

    @Test
    fun `A Float orZero() returns the Float if not null`() {
        assertEquals(23.0F, 23.0F.orZero())
    }

    @Test
    fun `A Float orZero() returns zero if null`() {
        val nullFloat: Float? = null
        assertEquals(0.0F, nullFloat.orZero())
    }

    @Test
    fun `round properly rounds to specified decimal places`() {
        assertEquals(3.14, 3.14159.round(2))
        assertEquals(3.142, 3.14159.round(3))
        assertEquals(3.0, 3.14159.round(0))
        assertEquals(3.8, 3.76.round(1))
        assertEquals(-2.35, (-2.346).round(2))
    }

    @Test
    fun `roundToText formats with padding and optional leading zero removal`() {
        assertEquals("3.14", 3.14159.roundToText(2))
        assertEquals("3.140", 3.14.roundToText(3, padToLength = true))
        assertEquals("3.14", 3.14.roundToText(3, padToLength = false))
        assertEquals(".300", 0.3.roundToText(3, padToLength = true, removeLeadingZero = true))
        assertEquals("0.300", 0.3.roundToText(3, padToLength = true, removeLeadingZero = false))
        assertEquals(".25", 0.254.roundToText(2, removeLeadingZero = true))
        assertEquals("12.35", 12.3456.roundToText(2))
    }

    @Test
    fun `withOrdinal returns proper ordinal string for numbers`() {
        assertEquals("0th", 0.withOrdinal())
        assertEquals("1st", 1.withOrdinal())
        assertEquals("2nd", 2.withOrdinal())
        assertEquals("3rd", 3.withOrdinal())
        assertEquals("4th", 4.withOrdinal())
        assertEquals("10th", 10.withOrdinal())
        assertEquals("11th", 11.withOrdinal())
        assertEquals("12th", 12.withOrdinal())
        assertEquals("13th", 13.withOrdinal())
        assertEquals("14th", 14.withOrdinal())
        assertEquals("21st", 21.withOrdinal())
        assertEquals("22nd", 22.withOrdinal())
        assertEquals("23rd", 23.withOrdinal())
        assertEquals("24th", 24.withOrdinal())
        assertEquals("101st", 101.withOrdinal())
        assertEquals("111th", 111.withOrdinal())
        assertEquals("112th", 112.withOrdinal())
        assertEquals("113th", 113.withOrdinal())
        assertEquals("121st", 121.withOrdinal())
        assertEquals("-1st", (-1).withOrdinal())
        assertEquals("-11th", (-11).withOrdinal())
    }
}