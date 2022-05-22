package dev.mfazio.utils.extensions

import kotlin.random.Random


fun <T> Collection<T>.printEach() = this.forEach(::println)

//From here: https://youtrack.jetbrains.com/issue/KT-4734
fun <K, V> Map<K, V?>.filterNotNullValues(): Map<K, V> =
    mapNotNull { (key, nullableValue) ->
        nullableValue?.let { key to it }
    }.toMap()

fun <T> List<T>?.randomOrNull(random: Random? = null): T? =
    if (random != null) {
        this?.shuffled(random)?.firstOrNull()
    } else {
        this?.shuffled()?.firstOrNull()
    }