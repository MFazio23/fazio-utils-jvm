package dev.mfazio.utils.random

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

class FazioRandomTest {

    @Nested
    @DisplayName("IntListRandom tests")
    inner class IntListRandomTest {
        @Test
        fun `Custom int list Random returns items as expected from nextInt()`() {
            val testList = listOf(55, 8, 12, 37, 15, 9)

            val random = IntListRandom(testList)

            testList.forEach {
                assertEquals(it, random.nextInt())
            }
        }

        @Test
        fun `Custom int list Random returns items as expected from nextInt(until)`() {
            val testList = listOf(55, 8, 12, 37, 15, 9)

            val random = IntListRandom(testList)

            testList.forEach {
                assertEquals(it, random.nextInt(100))
            }
        }

        @Test
        fun `Custom int list Random returns items as expected from nextInt(from, until)`() {
            val testList = listOf(55, 8, 12, 37, 15, 9)

            val random = IntListRandom(testList)

            testList.forEach {
                assertEquals(it, random.nextInt(0, 100))
            }
        }

        @Test
        fun `Custom int list Random can be reset`() {
            val testList = listOf(55, 8, 12, 37,)
            val actualResultList = listOf(55, 8, 12, -1, 55, 8, 12, 37)

            val random = IntListRandom(testList)

            actualResultList.forEach {
                if (it == -1) random.reset()
                else assertEquals(it, random.nextInt())
            }
        }
    }

    @Nested
    @DisplayName("DoubleListRandom tests")
    inner class DoubleListRandomTest {
        @Test
        fun `Custom double list Random returns items as expected from nextDouble()`() {
            val testList = listOf(55.0, 8.5, 12.1, 37.22, 15.0, 9.99)

            val random = DoubleListRandom(testList)

            testList.forEach {
                assertEquals(it, random.nextDouble())
            }
        }

        @Test
        fun `Custom double list Random returns items as expected from nextDouble(until)`() {
            val testList = listOf(55.0, 8.5, 12.1, 37.22, 15.0, 9.99)

            val random = DoubleListRandom(testList)

            testList.forEach {
                assertEquals(it, random.nextDouble(100.0))
            }
        }

        @Test
        fun `Custom double list Random returns items as expected from nextDouble(from, until)`() {
            val testList = listOf(55.0, 8.5, 12.1, 37.22, 15.0, 9.99)

            val random = DoubleListRandom(testList)

            testList.forEach {
                assertEquals(it, random.nextDouble(10.0, 50.0))
            }
        }

        @Test
        fun `Custom double list Random can be reset`() {
            val testList = listOf(55.0, 8.5, 12.1, 37.22,)
            val actualResultList = listOf(55.0, 8.5, 12.1, -1.0, 55.0, 8.5, 12.1, 37.22)

            val random = DoubleListRandom(testList)

            actualResultList.forEach {
                if (it == -1.0) random.reset()
                else assertEquals(it, random.nextDouble())
            }
        }
    }

    @Test
    fun `a single getBits test so I have coverage - I don't get why this is abstract while everything else is open, but there must be a good reason`() {
        val random: BaseRandom = IntListRandom(emptyList())

        assertEquals(4, random.nextBits(4))
    }
}