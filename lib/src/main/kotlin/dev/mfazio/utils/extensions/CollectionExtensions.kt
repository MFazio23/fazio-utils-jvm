package dev.mfazio.utils.extensions

fun <T> Iterable<T>.printEach(extraLines: Int = 0) = this.forEach {
    println(it)
    repeat(extraLines) {
        println()
    }
}

fun <K, V> Map<K, V>.printEach(extraLines: Int = 0) = this.entries.printEach(extraLines = extraLines)

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

fun <E> MutableList<E>.removeAndReturn(filter: (E) -> Boolean): List<E> =
    this.filter(filter).also { items -> this.removeAll(items) }

fun <E> MutableList<E>.removeFirstAndReturn(filter: (E) -> Boolean): E? =
    this.indexOfFirst(filter).let { index ->
        if (index != -1) this.removeAt(index) else null
    }