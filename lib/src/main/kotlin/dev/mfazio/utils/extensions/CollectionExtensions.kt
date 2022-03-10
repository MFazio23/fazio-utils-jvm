package dev.mfazio.utils.extensions

fun <T> Collection<T>.printEach() = this.forEach(::println)

//From here: https://youtrack.jetbrains.com/issue/KT-4734
fun <K, V> Map<K, V?>.filterNotNullValues(): Map<K, V> =
    mapNotNull { (key, nullableValue) ->
        nullableValue?.let { key to it }
    }.toMap()