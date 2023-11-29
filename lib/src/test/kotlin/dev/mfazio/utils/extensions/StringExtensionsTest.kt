package dev.mfazio.utils.extensions

import kotlin.test.*

class StringExtensionsTest {
    @Test
    fun `isNotNullOrEmpty() is true with a normal string`() {
        assertTrue("This is a normal string".isNotNullOrEmpty())
    }

    @Test
    fun `isNotNullOrEmpty() is true with a whitespace-only string`() {
        assertTrue("    ".isNotNullOrEmpty())
    }

    @Test
    fun `isNotNullOrEmpty() is false with an empty string`() {
        assertFalse("".isNotNullOrEmpty())
    }

    @Test
    fun `isNotNullOrEmpty() is false with null`() {
        val nullString: String? = null
        assertFalse(nullString.isNotNullOrEmpty())
    }

    @Test
    fun `isNotNullOrBlank() is true with a normal string`() {
        assertTrue("This is a normal string".isNotNullOrBlank())
    }

    @Test
    fun `isNotNullOrBlank() is false with a whitespace-only string`() {
        assertFalse("    ".isNotNullOrBlank())
    }

    @Test
    fun `isNotNullOrBlank() is false with an empty string`() {
        assertFalse("".isNotNullOrBlank())
    }

    @Test
    fun `isNotNullOrBlank() is false with null`() {
        val nullString: String? = null
        assertFalse(nullString.isNotNullOrBlank())
    }

    @Test
    fun `indexOfFirstOrNull() works for the first letter`() {
        val input = "neighborhood"
        assertEquals(0, input.indexOfFirstOrNull { it == 'n' })
    }

    @Test
    fun `indexOfFirstOrNull() works for a different letter`() {
        val input = "neighborhood"
        assertEquals(3, input.indexOfFirstOrNull { it == 'g' })
    }

    @Test
    fun `indexOfFirstOrNull() works for a different letter with multiple in the word`() {
        val input = "neighborhood"
        assertEquals(4, input.indexOfFirstOrNull { it == 'h' })
    }

    @Test
    fun `indexOfFirstOrNull() returns null when the letter is missing`() {
        val input = "neighborhood"
        assertNull(input.indexOfFirstOrNull { it == 'm' })
    }

    @Test
    fun `indexOfFirstOrNull() returns null when the input is null`() {
        val input: String? = null
        assertNull(input.indexOfFirstOrNull { it == 'm' })
    }

    @Test
    fun `indexOfLastOrNull() works for the first letter`() {
        val input = "neighborhood"
        assertEquals(0, input.indexOfLastOrNull { it == 'n' })
    }

    @Test
    fun `indexOfLastOrNull() works for a different letter`() {
        val input = "neighborhood"
        assertEquals(3, input.indexOfLastOrNull { it == 'g' })
    }

    @Test
    fun `indexOfLastOrNull() works for a different letter with multiple in the word`() {
        val input = "neighborhood"
        assertEquals(8, input.indexOfLastOrNull { it == 'h' })
    }

    @Test
    fun `indexOfLastOrNull() returns null when the letter is missing`() {
        val input = "neighborhood"
        assertNull(input.indexOfLastOrNull { it == 'm' })
    }

    @Test
    fun `indexOfLastOrNull() returns null when the input is null`() {
        val input: String? = null
        assertNull(input.indexOfLastOrNull { it == 'm' })
    }
}