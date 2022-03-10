package dev.mfazio.utils.random

interface RangeItem<T> {
    val chance: Double?

    fun getItemValue(): T?
    fun updateChance(newChance: Double): RangeItem<T>
}