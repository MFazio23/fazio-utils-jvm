package dev.mfazio.utils.extensions

fun <T> Collection<T>.printEach(extraLines: Int = 0) = this.forEach {
    println(it)
    repeat(extraLines) {
        println()
    }
}

//From here: https://youtrack.jetbrains.com/issue/KT-4734
fun <K, V> Map<K, V?>.filterNotNullValues(): Map<K, V> =
    mapNotNull { (key, nullableValue) ->
        nullableValue?.let { key to it }
    }.toMap()

fun <N : Number> Collection<N>.getOrDefault(index: Int, defaultValue: N) = this.elementAtOrNull(index) ?: defaultValue

fun <K> Map<K, Int>.getOrZero(key: K): Int = this.getOrDefault(key, 0)
fun <K> Map<K, Double>.getOrZero(key: K): Double = this.getOrDefault(key, 0.0)
fun <K> Map<K, Double>.getOrIntZero(key: K): Int = this.getOrDefault(key, 0.0).toInt()

fun <T> Collection<T>.crossProduct(otherCollection: Collection<T>): List<Pair<T, T>> =
    this.flatMap { first ->
        otherCollection.map { second ->
            first to second
        }
    }