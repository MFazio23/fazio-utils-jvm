package dev.mfazio.utils.extensions

import java.io.InputStream
import kotlin.reflect.KClass

fun <T : Any> KClass<T>.getResourceAsStream(path: String): InputStream? = this.java.classLoader.getResourceAsStream(path)
fun <T : Any> KClass<T>.getResourceAsString(path: String): String? = this.java.classLoader.getResource(path)?.readText()
fun <T : Any> KClass<T>.getResourceAsListOfStrings(path: String): List<String>
    = this.getResourceAsString(path)?.split(System.lineSeparator()) ?: emptyList()