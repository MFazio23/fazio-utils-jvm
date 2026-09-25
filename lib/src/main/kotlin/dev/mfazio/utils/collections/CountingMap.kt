package dev.mfazio.utils.collections

import dev.mfazio.utils.extensions.getOrZero

class CountingMap<K>(private val delegate: MutableMap<K, Int> = mutableMapOf()) :
    Map<K, Int> by delegate {
    fun increment(key: K, amount: Int = 1): Int {
        val newValue = delegate.getOrZero(key) + (amount)
        delegate[key] = newValue

        return newValue
    }

    fun decrement(key: K, amount: Int = 1): Int {
        val newValue = delegate.getOrZero(key) - (amount)
        delegate[key] = newValue

        return newValue
    }

    operator fun plusAssign(key: K) {
        increment(key)
    }

    operator fun plusAssign(pair: Pair<K, Int>) {
        val (key, amount) = pair
        increment(key, amount)
    }

    operator fun plusAssign(other: Map<K, Int>) {
        other.forEach { increment(it.key, it.value) }
    }

    operator fun minusAssign(key: K) {
        decrement(key)
    }

    operator fun minusAssign(pair: Pair<K, Int>) {
        val (key, amount) = pair
        decrement(key, amount)
    }

    operator fun minusAssign(other: Map<K, Int>) {
        other.forEach { decrement(it.key, it.value) }
    }

    override fun get(key: K): Int = getOrZero(key)
    operator fun set(key: K, count: Int) {
        delegate[key] = count
    }

    fun getCount(key: K): Int = delegate[key] ?: 0

    override fun toString(): String = delegate.toString()
    override fun equals(other: Any?): Boolean = delegate == other
    override fun hashCode(): Int = delegate.hashCode()

    companion object {
        fun <K> fromList(initialList: List<K>): CountingMap<K> =
            countsOf(initialList)

        fun <K> fromMap(map: Map<K, Int>): CountingMap<K> =
            CountingMap(map.toMutableMap())

        fun <K> countsOf(items: Iterable<K>): CountingMap<K> =
            CountingMap(items.groupingBy { it }.eachCount().toMutableMap())
    }
}

fun <K> countingMapOf(vararg keys: K): CountingMap<K> =
    CountingMap.fromList(listOf(*keys))

fun <K> countingMapOf(vararg pairs: Pair<K, Int>): CountingMap<K> =
    CountingMap(mutableMapOf(*pairs))

fun <K> Iterable<K>.toCountingMap(): CountingMap<K> = CountingMap.countsOf(this)