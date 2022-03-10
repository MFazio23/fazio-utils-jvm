package dev.mfazio.utils.random

data class RangeValue<T>(
    val value: T,
    override val chance: Double? = null,
) : RangeItem<T> {
    override fun getItemValue() = value
    override fun updateChance(newChance: Double) = this.copy(chance = newChance)
}