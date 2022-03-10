package dev.mfazio.utils.random

import kotlin.random.Random

data class RangeGroup<T>(
    val items: List<RangeItem<T>>,
    override val chance: Double? = null,
    private val random: Random = Random.Default,
) : RangeItem<T> {

    fun settleChances(): RangeGroup<T> {
        val defaultCount = items.count { item -> item.chance == null }

        val remaining = defaultTotal -
            items.filter { it.chance != null }.sumOf { item -> item.chance ?: 0.0 }

        val newItems = items.map { item ->
            item.updateChance(
                this.chance ?: (remaining / defaultCount)
            )
        }

        return this.copy(items = newItems)
    }

    override fun updateChance(newChance: Double) = this.copy(chance = newChance)

    override fun getItemValue(): T? {
        val totalChance = items.sumOf { it.chance ?: 0.0 }
        return if (totalChance <= 0.0) {
            items.firstOrNull()?.getItemValue()
        } else {
            random.nextDouble(totalChance).let { rand ->
                items.fold(0.0) { start, item ->
                    val startValue = (start + (item.chance ?: 0.0))
                    if (rand < startValue) {
                        return@let item.getItemValue()
                    }

                    start + (item.chance ?: 0.0)
                }

                items.lastOrNull()?.getItemValue()
            }
        }
    }

    override fun toString(): String = "RangeGroup(chance=$chance, items=$items)"

    companion object {
        private const val defaultTotal = 100.0

        fun <T> fromMap(map: Map<T, Double?>) =
            RangeGroup(map.map { (item, chance) ->
                RangeValue(
                    value = item,
                    chance = chance,
                )
            }).settleChances()
    }
}