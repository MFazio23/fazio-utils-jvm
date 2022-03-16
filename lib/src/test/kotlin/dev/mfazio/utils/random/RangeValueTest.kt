package dev.mfazio.utils.random

import kotlin.test.Test
import kotlin.test.assertEquals

class RangeValueTest {
    @Test
    fun `Values are set correctly on RangeValue objects`() {
        val testValue = "Test value"
        val rangeValue = RangeValue(testValue)

        assertEquals(testValue, rangeValue.value)
        assertEquals(testValue, rangeValue.getItemValue())
    }

    @Test
    fun `Nullable values are set correctly on RangeValue objects`() {
        val testValue: String? = "Test value"
        val rangeValue = RangeValue<String?>(testValue)

        assertEquals(testValue, rangeValue.value)
        assertEquals(testValue, rangeValue.getItemValue())

        val nullTestValue: String? = null
        val rangeValueWithNull = RangeValue<String?>(nullTestValue)

        assertEquals(nullTestValue, rangeValueWithNull.value)
        assertEquals(nullTestValue, rangeValueWithNull.getItemValue())
    }

    @Test
    fun `Can set and update chances on RangeValue objects`() {
        val testValue = "New test value"
        val testChance = 10.5
        val rangeValue = RangeValue(testValue, testChance)

        assertEquals(rangeValue.chance, testChance)

        val newTestChance = 23.10
        val newRangeValue = rangeValue.updateChance(newTestChance)
        assertEquals(rangeValue.chance, testChance)
        assertEquals(newRangeValue.chance, newTestChance)
    }
}