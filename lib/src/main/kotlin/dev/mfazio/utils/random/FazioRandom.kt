package dev.mfazio.utils.random

import kotlin.random.Random

abstract class BaseRandom : Random() {
    // For some reason, this is abstract while all the other functions are just open.
    // I don't get it, but they know far more than I do.
    override fun nextBits(bitCount: Int): Int = bitCount
}

class IntListRandom(val results: List<Int>) : BaseRandom() {
    private var counter = 0
    override fun nextInt(): Int = results[counter++]

    override fun nextInt(until: Int): Int = results[counter++]

    override fun nextInt(from: Int, until: Int): Int = results[counter++]

    fun reset() {
        counter = 0
    }
}

class DoubleListRandom(val results: List<Double>) : BaseRandom() {
    private var counter = 0
    override fun nextDouble(): Double = results[counter++]

    override fun nextDouble(until: Double): Double = results[counter++]

    override fun nextDouble(from: Double, until: Double): Double = results[counter++]

    fun reset() {
        counter = 0
    }
}