package dev.mfazio.utils.math

import kotlin.test.*

class LeastCommonTest {
    @Test
    fun `Test least common multiple for two integers`() {
        assertEquals(12, findLCM(3, 4))
        assertEquals(12, findLCM(4, 3))
        assertEquals(12, findLCM(4, 12))
        assertEquals(15, findLCM(5, 3))
        assertEquals(35, findLCM(7, 5))
        assertEquals(161, findLCM(7, 23))
        assertEquals(230, findLCM(10, 23))
        assertEquals(2_942_466, findLCM(1654, 3558))
    }

    @Test
    fun `Test least common multiple for two longs`() {
        assertEquals(12L, findLCM(3L, 4L))
        assertEquals(12L, findLCM(4L, 3L))
        assertEquals(12L, findLCM(4L, 12L))
        assertEquals(15L, findLCM(5L, 3L))
        assertEquals(35L, findLCM(7L, 5L))
        assertEquals(161L, findLCM(7L, 23L))
        assertEquals(230L, findLCM(10L, 23L))
        assertEquals(46_628_172L, findLCM(6813L, 6844L))
    }

    @Test
    fun `Test least common multiple for a list of integers`() {
        assertEquals(12, listOf(3, 4).findLCM())
        assertEquals(60, listOf(3, 4, 5).findLCM())
        assertEquals(60, listOf(3, 4, 5, 6).findLCM())
        assertEquals(60, listOf(15, 4, 12, 10).findLCM())
        assertEquals(4620, listOf(15, 220, 140, 20).findLCM())
        assertEquals(1_372_680, listOf(615, 135, 31, 8).findLCM())
    }

    @Test
    fun `Test least common multiple for a list of longs`() {
        assertEquals(12L, listOf(3L, 4L).findLCM())
        assertEquals(246_960L, listOf(784L, 686L, 315L).findLCM())
        assertEquals(240_574_319_910L, listOf(6813L, 214L, 122L, 10L, 541L).findLCM())
    }

    @Test
    fun `Test least common multiple for a list of large longs`() {
        assertEquals(10_241_191_004_509L, listOf(13207L, 22199L, 14893L, 16579L, 20513L, 12083L).findLCM())
        assertEquals(92_643_584_114_427_984L, listOf(13584L, 13864L, 24123L, 10578L, 35811L).findLCM())
    }
}