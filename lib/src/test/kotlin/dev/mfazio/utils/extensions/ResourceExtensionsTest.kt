package dev.mfazio.utils.extensions

import kotlin.test.*

class ResourceExtensionsTest {
    @Test
    fun `KClass getResourceAsStream successfully returns an InputStream`() {
        val inputStream = ResourceExtensionsTest::class.getResourceAsStream(fileName)

        assertNotNull(inputStream)

        val inputString = inputStream.bufferedReader().readText()

        assertEquals(fileContents, inputString)
    }

    @Test
    fun `KClass getResourceAsStream doesn't error out when the resource cannot be found`() {
        val inputStream = ResourceExtensionsTest::class.getResourceAsStream(invalidFileName)

        assertNull(inputStream)
    }

    @Test
    fun `KClass getResourceAsString successfully returns a String`() {
        val inputString = ResourceExtensionsTest::class.getResourceAsString(fileName)

        assertEquals(fileContents, inputString)
    }

    @Test
    fun `KClass getResourceAsString doesn't error out when the resource cannot be found`() {
        val inputStream = ResourceExtensionsTest::class.getResourceAsString(invalidFileName)

        assertNull(inputStream)
    }

    @Test
    fun `KClass getResourceAsListOfStrings successfully returns a List of Strings`() {
        val inputList = ResourceExtensionsTest::class.getResourceAsListOfStrings(listFileName)

        assertContentEquals(listFileContents, inputList)
    }

    @Test
    fun `KClass getResourceAsListOfStrings returns an empty list when the resource cannot be found`() {
        val inputList = getResourceAsListOfStrings(invalidFileName)

        assertEquals(emptyList(), inputList)
    }

    @Test
    fun `getResourceAsStream successfully returns an InputStream`() {
        val inputStream = getResourceAsStream(fileName)

        assertNotNull(inputStream)

        val inputString = inputStream.bufferedReader().readText()

        assertEquals(fileContents, inputString)
    }

    @Test
    fun `getResourceAsStream doesn't error out when the resource cannot be found`() {
        val inputStream = getResourceAsStream(invalidFileName)

        assertNull(inputStream)
    }

    @Test
    fun `getResourceAsString successfully returns a String`() {
        val inputString = getResourceAsString(fileName)

        assertEquals(fileContents, inputString)
    }

    @Test
    fun `getResourceAsString doesn't error out when the resource cannot be found`() {
        val inputStream = getResourceAsString(invalidFileName)

        assertNull(inputStream)
    }

    @Test
    fun `getResourceAsListOfStrings successfully returns a List of Strings`() {
        val inputList = getResourceAsListOfStrings(listFileName)

        assertContentEquals(listFileContents, inputList)
    }

    @Test
    fun `getResourceAsListOfStrings returns an empty list when the resource cannot be found`() {
        val inputList = getResourceAsListOfStrings(invalidFileName)

        assertEquals(emptyList(), inputList)
    }

    companion object {
        private const val fileName = "resource-extensions-test.txt"
        private const val listFileName = "resource-extensions-test-list.txt"
        private const val invalidFileName = "resource-extensions-test-missing-file.txt"
        private const val fileContents = "The test was successful, yay!"

        private val listFileContents = listOf(
            "This",
            "is",
            "a",
            "list",
            "of",
            "string",
            "values",
        )
    }
}