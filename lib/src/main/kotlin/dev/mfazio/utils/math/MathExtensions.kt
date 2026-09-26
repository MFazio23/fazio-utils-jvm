package dev.mfazio.utils.math

fun Collection<Int>.product() = this.reduce { acc, i -> acc * i }

fun Collection<Long>.product() = this.reduce { acc, i -> acc * i }

@JvmName("intMedian")
fun Collection<Int>.median(): Double? = when {
    this.isEmpty() -> null
    this.size == 1 -> this.first().toDouble()
    else -> {
        this.sorted().let {
            val mid = it.size / 2
            if (it.size % 2 == 0) {
                (it[mid - 1] + it[mid]) / 2.0
            } else it[mid].toDouble()
        }
    }
}

@JvmName("doubleMedian")
fun Collection<Double>.median(): Double? = when {
    this.isEmpty() -> null
    this.size == 1 -> this.first()
    else -> {
        this.sorted().let {
            val mid = it.size / 2
            if (it.size % 2 == 0) {
                (it[mid - 1] + it[mid]) / 2.0
            } else it[mid]
        }
    }
}
