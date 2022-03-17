package dev.mfazio.utils.extensions

import java.time.DayOfWeek
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DayOfWeekExtensionsTest {
    @Test
    fun `isWeekend is false for Monday`() {
        assertFalse(DayOfWeek.MONDAY.isWeekend())
    }

    @Test
    fun `isWeekend is true for Saturday and Sunday`() {
        assertTrue(DayOfWeek.SATURDAY.isWeekend())
        assertTrue(DayOfWeek.SUNDAY.isWeekend())
    }

    @Test
    fun `isWeekday is true for Wednesday and Friday`() {
        assertTrue(DayOfWeek.WEDNESDAY.isWeekday())
        assertTrue(DayOfWeek.FRIDAY.isWeekday())
    }

    @Test
    fun `isWeekday is false for Saturday and Sunday`() {
        assertFalse(DayOfWeek.SATURDAY.isWeekday())
        assertFalse(DayOfWeek.SUNDAY.isWeekday())
    }
}