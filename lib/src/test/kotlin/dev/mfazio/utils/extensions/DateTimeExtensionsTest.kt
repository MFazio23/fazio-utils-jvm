package dev.mfazio.utils.extensions

import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DateTimeExtensionsTest {
    @Test
    fun `isBetween() is true when date is between two other dates`() {
        val start = LocalDateTime.now().minusDays(6)
        val end = LocalDateTime.now().plusHours(14)
        val date = LocalDateTime.now().plusMinutes(64)

        assertTrue(date.isBetween(start, end))
    }

    @Test
    fun `isBetween() is true when date is equal to starting date`() {
        val start = LocalDateTime.now().minusDays(6)
        val end = LocalDateTime.now().plusHours(14)
        val date = LocalDateTime.now().minusDays(6)

        assertTrue(date.isBetween(start, end))
    }

    @Test
    fun `isBetween() is true when date is equal to end date`() {
        val start = LocalDateTime.now().minusDays(6)
        val end = LocalDateTime.now().plusHours(14)
        val date = LocalDateTime.now().plusHours(14)

        assertTrue(date.isBetween(start, end))
    }

    @Test
    fun `isBetween() is true when date is between now and later date`() {
        val end = LocalDateTime.now().plusHours(14)
        val date = LocalDateTime.now().plusMinutes(64)

        assertTrue(date.isBetween(end = end))
    }

    @Test
    fun `isBetween() is true when date is between earlier date and now`() {
        val start = LocalDateTime.now().minusDays(6)
        val date = LocalDateTime.now().minusMinutes(64)

        assertTrue(date.isBetween(start = start))
    }

    @Test
    fun `isBetween() is false when date is before start date`() {
        val start = LocalDateTime.now().minusDays(6)
        val end = LocalDateTime.now().plusHours(14)
        val date = LocalDateTime.now().minusDays(23)

        assertFalse(date.isBetween(start, end))
    }

    @Test
    fun `isBetween() is false when date is after end date`() {
        val start = LocalDateTime.now().minusDays(6)
        val end = LocalDateTime.now().plusHours(14)
        val date = LocalDateTime.now().plusDays(5)

        assertFalse(date.isBetween(start, end))
    }

    @Test
    fun `ordinal suffix from first of month is correct`() {
        val date = LocalDate.now().withDayOfMonth(1)

        assertEquals("st", date.getOrdinalSuffix())
    }

    @Test
    fun `ordinal suffix from second of month is correct`() {
        val date = LocalDate.now().withDayOfMonth(2)

        assertEquals("nd", date.getOrdinalSuffix())
    }

    @Test
    fun `ordinal suffix from third of month is correct`() {
        val date = LocalDate.now().withDayOfMonth(3)

        assertEquals("rd", date.getOrdinalSuffix())
    }

    @Test
    fun `ordinal suffix from middle (th) day of month is correct`() {
        val date = LocalDate.now().withDayOfMonth(10)

        assertEquals("th", date.getOrdinalSuffix())
    }

    @Test
    fun `ordinal suffix for 21st through 23rd is correct`() {
        assertEquals(
            "st",
            LocalDate.now().withDayOfMonth(21).getOrdinalSuffix()
        )
        assertEquals(
            "nd",
            LocalDate.now().withDayOfMonth(22).getOrdinalSuffix()
        )
        assertEquals(
            "rd",
            LocalDate.now().withDayOfMonth(23).getOrdinalSuffix()
        )
    }

    @Test
    fun `ordinal suffix for 30th and 31st is correct`() {
        val december = LocalDate.now().withMonth(12)
        assertEquals(
            "th",
            december.withDayOfMonth(30).getOrdinalSuffix()
        )
        assertEquals(
            "st",
            december.withDayOfMonth(31).getOrdinalSuffix()
        )
    }

    @Test
    fun `ordinal suffix works with LocalDateTime`() {
        val now = LocalDateTime.now()
        assertEquals(
            "st",
            now.withDayOfMonth(1).getOrdinalSuffix()
        )
        assertEquals(
            "nd",
            now.withDayOfMonth(2).getOrdinalSuffix()
        )
        assertEquals(
            "rd",
            now.withDayOfMonth(3).getOrdinalSuffix()
        )
        assertEquals(
            "th",
            now.withDayOfMonth(4).getOrdinalSuffix()
        )
        assertEquals(
            "th",
            now.withDayOfMonth(20).getOrdinalSuffix()
        )
        assertEquals(
            "st",
        now.withDayOfMonth(21).getOrdinalSuffix()
        )
        assertEquals(
            "nd",
            now.withDayOfMonth(22).getOrdinalSuffix()
        )
        assertEquals(
            "rd",
            now.withDayOfMonth(23).getOrdinalSuffix()
        )
    }

    @Test
    fun `ordinal suffix works with OffsetDateTime`() {
        val now = OffsetDateTime.now()
        assertEquals(
            "st",
            now.withDayOfMonth(1).getOrdinalSuffix()
        )
        assertEquals(
            "nd",
            now.withDayOfMonth(2).getOrdinalSuffix()
        )
        assertEquals(
            "rd",
            now.withDayOfMonth(3).getOrdinalSuffix()
        )
        assertEquals(
            "th",
            now.withDayOfMonth(4).getOrdinalSuffix()
        )
        assertEquals(
            "th",
            now.withDayOfMonth(20).getOrdinalSuffix()
        )
        assertEquals(
            "st",
        now.withDayOfMonth(21).getOrdinalSuffix()
        )
        assertEquals(
            "nd",
            now.withDayOfMonth(22).getOrdinalSuffix()
        )
        assertEquals(
            "rd",
            now.withDayOfMonth(23).getOrdinalSuffix()
        )
    }

    @Test
    fun `ordinal suffix works with ZonedDateTime`() {
        val now = ZonedDateTime.now()
        assertEquals(
            "st",
            now.withDayOfMonth(1).getOrdinalSuffix()
        )
        assertEquals(
            "nd",
            now.withDayOfMonth(2).getOrdinalSuffix()
        )
        assertEquals(
            "rd",
            now.withDayOfMonth(3).getOrdinalSuffix()
        )
        assertEquals(
            "th",
            now.withDayOfMonth(4).getOrdinalSuffix()
        )
        assertEquals(
            "th",
            now.withDayOfMonth(20).getOrdinalSuffix()
        )
        assertEquals(
            "st",
        now.withDayOfMonth(21).getOrdinalSuffix()
        )
        assertEquals(
            "nd",
            now.withDayOfMonth(22).getOrdinalSuffix()
        )
        assertEquals(
            "rd",
            now.withDayOfMonth(23).getOrdinalSuffix()
        )
    }
}