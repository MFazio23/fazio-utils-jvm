package dev.mfazio.utils.extensions

import java.io.InputStream
import kotlin.reflect.KClass

fun <T : Any> KClass<T>.getResourceAsStream(path: String): InputStream? = this.java.classLoader.getResourceAsStream(path)
fun <T : Any> KClass<T>.getResourceAsString(path: String): String? = this.java.classLoader.getResource(path)?.readText()

// This function isn't being read correctly by Kover, so I'm writing it this way. 🤷
fun <T : Any> KClass<T>.getResourceAsListOfStrings(path: String): List<String> {
    val resourceString = getResourceAsString(path)

    val result = resourceString?.split(System.lineSeparator())

    return result ?: emptyList()
}

fun getResourceAsStream(path: String): InputStream? = {}::class.getResourceAsStream(path)
fun getResourceAsString(path: String): String? = {}::class.getResourceAsString(path)
fun getResourceAsListOfStrings(path: String): List<String> = {}::class.getResourceAsListOfStrings(path)