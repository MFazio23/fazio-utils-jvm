package dev.mfazio.utils.random

import org.junit.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertTrue

class RandomItemsTest {

    @Test
    fun `Flipping a coin works`() {
        testDiceRolling(2) {
            if (flipCoin()) 1 else 2
        }
    }

    @Test
    fun `Flipping a coin works with items`() {
        testDiceRolling(2) {
            coinFlip(1, 2)
        }
    }

    @Test
    fun `Flipping a coin with items works`() {
        testDiceRolling(4, rollFunction = ::rollD4)
    }

    @Test
    fun `Rolling a D4 works`() {
        testDiceRolling(4, rollFunction = ::rollD4)
    }

    @Test
    fun `Rolling a D6 works`() {
        testDiceRolling(6, rollFunction = ::rollD6)
    }

    @Test
    fun `Rolling a D8 works`() {
        testDiceRolling(8, rollFunction = ::rollD8)
    }

    @Test
    fun `Rolling a D10 works`() {
        testDiceRolling(10, rollFunction = ::rollD10)
    }

    @Test
    fun `Rolling a D12 works`() {
        testDiceRolling(12, rollFunction = ::rollD12)
    }

    @Test
    fun `Rolling a D20 works`() {
        testDiceRolling(20, rollFunction = ::rollD20)
    }

    @Test
    fun `Rolling a D100 works`() {
        testDiceRolling(100, rollFunction = ::rollD100)
    }

    private fun testDiceRolling(
        sidesOnDice: Int,
        rollCountFactor: Int = defaultRollCountFactor,
        rollResultThreshold: Double = defaultRollResultThreshold,
        rollFunction: () -> Int,
    ) {
        val rollResults = (0..(sidesOnDice * rollCountFactor)).map {
            rollFunction()
        }.groupingBy { it }.eachCount()

        val possibleValues = (1..sidesOnDice).toList()

        assertContentEquals(rollResults.keys.sorted(), possibleValues)

        val lowCount = rollResults.values.minOrNull()?.toDouble() ?: 0.0
        val highCount = rollResults.values.maxOrNull()?.toDouble() ?: 0.0

        println("Diff: ${(highCount - lowCount) / rollCountFactor}")

        assertTrue(rollResultThreshold >= (highCount - lowCount) / rollCountFactor)
    }

    companion object {
        private const val defaultRollCountFactor = 100_000
        private const val defaultRollResultThreshold = 0.025
    }
}