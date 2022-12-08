package dev.mfazio.utils.extensions

fun <T> Collection<T>.printEach() = this.forEach(::println)

//From here: https://youtrack.jetbrains.com/issue/KT-4734
fun <K, V> Map<K, V?>.filterNotNullValues(): Map<K, V> =
    mapNotNull { (key, nullableValue) ->
        nullableValue?.let { key to it }
    }.toMap()

fun <N : Number> List<N>.getOrDefault(index: Int, defaultValue: N) = this.getOrNull(index) ?: defaultValue

fun <K> Map<K, Int>.getOrZero(key: K): Int = this.getOrDefault(key, 0)
fun <K> Map<K, Double>.getOrZero(key: K): Double = this.getOrDefault(key, 0.0)
fun <K> Map<K, Double>.getOrIntZero(key: K): Int = this.getOrDefault(key, 0.0).toInt()