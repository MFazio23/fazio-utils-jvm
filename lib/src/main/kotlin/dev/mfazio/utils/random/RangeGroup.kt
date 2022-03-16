package dev.mfazio.utils.random

import kotlin.random.Random

data class RangeGroup<T>(
    val items: List<RangeItem<T>>,
    override val chance: Double? = null,
    val total: Double = defaultTotal,
    private val random: Random = Random.Default,
) : RangeItem<T> {

    fun settleChances(): RangeGroup<T> {
        val defaultCount = items.count { item -> item.chance == null }
        val currentTotal = items.filter { it.chance != null }.sumOf { item -> item.chance ?: 0.0 }

        val remaining = if (defaultCount == 0) 0.0 else total - currentTotal

        val newItems = items.map { item ->
            if (defaultCount == 0) item else {
                item.updateChance(
                    item.chance ?: (remaining / defaultCount)
                )
            }
        }

        return this.copy(items = newItems)
    }

    override fun updateChance(newChance: Double) = this.copy(chance = newChance)

    override fun getItemValue(): T? {
        val totalChance = items.sumOf { it.chance ?: 0.0 }
        return when {
            items.all { (it.chance ?: 0.0) < 0.0 } -> {
                null
            }
            totalChance <= 0.0 -> {
                items.firstOrNull { (it.chance ?: 0.0) > 0.0 }?.getItemValue()
            }
            else -> {
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