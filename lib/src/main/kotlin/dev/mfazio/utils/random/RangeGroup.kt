package dev.mfazio.utils.random

import kotlin.random.Random

data class RangeGroup<T>(
    val items: Collection<RangeItem<T>>,
    override val chance: Double? = null,
    val total: Double = defaultTotal,
    private val random: Random = Random.Default,
) : RangeItem<T> {

    fun settleChances(): RangeGroup<T> {
        val validItems = items.filter { (it.chance ?: 0.0) >= 0.0 }
        val defaultCount = validItems.count { item -> item.chance == null }
        val currentTotal = validItems.filter { it.chance != null }.sumOf { item -> item.chance ?: 0.0 }

        val remaining = if (defaultCount == 0) 0.0 else total - currentTotal

        val newItems = validItems.map { item ->
            if (defaultCount == 0) item else item.updateChance(item.chance ?: (remaining / defaultCount))
        }

        return this.copy(items = newItems)
    }

    override fun updateChance(newChance: Double) = this.copy(chance = newChance)

    override fun getItemValue(): T? = getItemValue(true)

    fun getItemValue(shouldSettle: Boolean = true): T? {
        val groupToUse = if (shouldSettle) this.settleChances() else this
        val totalChance = groupToUse.items.sumOf { it.chance ?: 0.0 }
        return when {
            groupToUse.items.all { (it.chance ?: 0.0) < 0.0 } || totalChance <= 0.0 -> {
                null
            }
            else -> {
                random.nextDouble(totalChance).let { rand ->
                    groupToUse.items.fold(0.0) { start, item ->
                        val startValue = (start + (item.chance ?: 0.0))
                        if (rand < startValue) {
                            return@let item.getItemValue()
                        }

                        start + (item.chance ?: 0.0)
                    }

                    groupToUse.items.lastOrNull()?.getItemValue()
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