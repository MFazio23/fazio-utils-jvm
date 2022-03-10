package dev.mfazio.utils.extensions

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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
}