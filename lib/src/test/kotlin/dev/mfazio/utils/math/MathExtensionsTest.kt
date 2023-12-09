package dev.mfazio.utils.math

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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
}