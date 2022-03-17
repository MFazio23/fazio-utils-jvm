package dev.mfazio.utils.random

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.*

class RangeGroupTest {
    @Nested
    @DisplayName("Basic Functionality")
    inner class BasicFunctionalityTest {
        @Test
        fun `Setting values works on a RangeGroup`() {
            val testRangeValues = listOf(
                RangeValue("test value A"),
                RangeValue("test value B"),
                RangeValue("test value C"),
            )
            val testRangeGroup = RangeGroup(testRangeValues)

            assertContentEquals(testRangeValues, testRangeGroup.items)
        }

        @Test
        fun `Setting chances works on a RangeGroup`() {
            val testChance = 10.5
            val testRangeGroup = RangeGroup(listOf(RangeValue("Test value")), testChance)

            assertEquals(testChance, testRangeGroup.chance)
        }

        @Test
        fun `Updating chances works on a RangeGroup`() {
            val testChance = 10.5
            val testRangeGroup = RangeGroup(listOf(RangeValue("Test value")), testChance)

            assertEquals(testChance, testRangeGroup.chance)

            val newTestChance = 25.24
            val newTestRangeGroup = testRangeGroup.updateChance(newTestChance)
            assertEquals(newTestChance, newTestRangeGroup.chance)
        }
    }

    @Nested
    @DisplayName("Settle Chances")
    inner class SettleChancesTest {
        @Test
        fun `Settling chances with no default items keeps everything as-is`() {
            val rangeGroupFromMap = RangeGroup.fromMap(
                mapOf(
                    "test value A" to 23.0,
                    "test value B" to 17.0,
                    "test value C" to 26.0,
                )
            )

            val correctValues = mapOf(
                "test value A" to 23.0,
                "test value B" to 17.0,
                "test value C" to 26.0,
            )

            val settledValues = rangeGroupFromMap.settleChances().items.associate { it.getItemValue() to it.chance }

            correctValues.forEach { (value, chance) ->
                assertEquals(chance, settledValues[value])
            }
        }

        @Test
        fun `Settling chances with one default item gives the default item a chance`() {
            val rangeGroup = RangeGroup(
                listOf(
                    RangeValue("Item A", 17.0),
                    RangeValue("Item B", 23.0),
                    RangeValue("Item C", 20.0),
                    RangeValue("Default item"),
                )
            )

            val settledItems = rangeGroup.settleChances().items.associate { it.getItemValue() to it.chance }

            assertEquals(17.0, settledItems["Item A"])
            assertEquals(23.0, settledItems["Item B"])
            assertEquals(20.0, settledItems["Item C"])
            assertEquals(40.0, settledItems["Default item"])
        }

        @Test
        fun `Settling chances with two default items splits the remaining amount`() {
            val rangeGroup = RangeGroup(
                listOf(
                    RangeValue("Default item A"),
                    RangeValue("Item A", 13.0),
                    RangeValue("Item B", 17.0),
                    RangeValue("Default item B"),
                    RangeValue("Item C", 12.0),
                )
            )

            val settledItems = rangeGroup.settleChances().items.associate { it.getItemValue() to it.chance }

            assertEquals(13.0, settledItems["Item A"])
            assertEquals(17.0, settledItems["Item B"])
            assertEquals(12.0, settledItems["Item C"])
            assertEquals(29.0, settledItems["Default item A"])
            assertEquals(29.0, settledItems["Default item B"])
        }

        @Test
        fun `Settling chances with a total over the default still distributes properly`() {
            val rangeGroup = RangeGroup(
                listOf(
                    RangeValue("Item A", 45.0),
                    RangeValue("Item B", 62.0),
                    RangeValue("Item C", 58.0),
                )
            )

            val settledItems = rangeGroup.settleChances().items.associate { it.getItemValue() to it.chance }

            assertEquals(45.0, settledItems["Item A"])
            assertEquals(62.0, settledItems["Item B"])
            assertEquals(58.0, settledItems["Item C"])
        }
    }

    @Nested
    @DisplayName("Get Items")
    inner class GetItemTest {

        @Test
        fun `Getting item values a bunch of times distributes according to the chance of each item`() {
            val runs = 1_000_000
            val marginForError = 0.02
            val itemList = listOf(
                RangeValue("Item A", 55.0),
                RangeValue("Item B", 15.0),
                RangeValue("Item C", 30.0),
            )
            val rangeGroup = RangeGroup(itemList)

            val rangeResults = rangeGroup.items.associate {
                it.getItemValue() to 0
            }.toMutableMap()

            repeat(runs) {
                val result = rangeGroup.getItemValue()
                rangeResults[result] = (rangeResults[result] ?: 0) + 1
            }

            itemList.forEach { rangeValue ->
                val resultTotal = rangeResults[rangeValue.value] ?: 0
                val chance = rangeValue.chance ?: 0.0
                val totalPercentage = resultTotal.toDouble() / (runs / 100)
                assertTrue(totalPercentage in ((chance * (1 - marginForError))..(chance * (1 + marginForError))))
            }
        }

        @Test
        fun `Getting multi-level item values a bunch of times distributes according to the chance of each item`() {
            val runs = 1_000_000
            val marginForError = 0.02
            val itemList = listOf(
                RangeGroup(
                    listOf(
                        RangeValue("Item A1", 10.0),
                        RangeValue("Item A2", 40.0),
                        RangeValue("Item A3", 30.0),
                        RangeValue("Item A4", 20.0),
                    ), 50.0
                ),
                RangeValue("Item B", 15.0),
                RangeValue("Item C", 35.0),
            )
            val rangeGroup = RangeGroup(itemList)

            val rangeResults = rangeGroup.items.associate {
                it.getItemValue() to 0
            }.toMutableMap()

            repeat(runs) {
                val result = rangeGroup.getItemValue()
                rangeResults[result] = (rangeResults[result] ?: 0) + 1
            }

            val expectedResults = listOf(
                "Item A1" to 5.0,
                "Item A2" to 20.0,
                "Item A3" to 15.0,
                "Item A4" to 10.0,
                "Item B" to 15.0,
                "Item C" to 35.0,
            )

            expectedResults.forEach { (value, chance) ->
                val resultTotal = rangeResults[value] ?: 0
                val totalPercentage = resultTotal.toDouble() / (runs / 100)
                assertTrue(totalPercentage in ((chance * (1 - marginForError))..(chance * (1 + marginForError))))
            }
        }

        @Test
        fun `Getting multiple multi-level item values a bunch of times distributes according to the chance of each item`() {
            val runs = 1_000_000
            val marginForError = 0.02
            val itemList = listOf(
                RangeGroup(
                    listOf(
                        RangeValue("Item A1", 10.0),
                        RangeValue("Item A2", 40.0),
                        RangeValue("Item A3", 30.0),
                        RangeValue("Item A4", 20.0),
                    ), 50.0
                ),
                RangeValue("Item B", 40.0),
                RangeGroup(
                    listOf(
                        RangeValue("Item C1", 30.0),
                        RangeValue("Item C2", 50.0),
                        RangeValue("Item C3", 20.0),
                    ), 10.0
                ),
            )
            val rangeGroup = RangeGroup(itemList)

            val rangeResults = rangeGroup.items.associate {
                it.getItemValue() to 0
            }.toMutableMap()

            repeat(runs) {
                val result = rangeGroup.getItemValue()
                rangeResults[result] = (rangeResults[result] ?: 0) + 1
            }

            val expectedResults = listOf(
                "Item A1" to 5.0,
                "Item A2" to 20.0,
                "Item A3" to 15.0,
                "Item A4" to 10.0,
                "Item B" to 40.0,
                "Item C1" to 3.0,
                "Item C2" to 5.0,
                "Item C3" to 2.0,
            )

            expectedResults.forEach { (value, chance) ->
                val resultTotal = rangeResults[value] ?: 0
                val totalPercentage = resultTotal.toDouble() / (runs / 100)
                assertTrue(totalPercentage in ((chance * (1 - marginForError))..(chance * (1 + marginForError))))
            }
        }

        @Test
        fun `Getting items with a default works as expected based on the random number`() {
            val random = DoubleListRandom(listOf(55.0, 75.5, 22.3, 99.0, 38.2))

            val rangeGroup = RangeGroup(
                listOf(
                    RangeValue("Item A", 23.3),
                    RangeValue("Item B", 42.1),
                    RangeValue("Item C", chance = null),
                    RangeValue("Item D", 10.66),
                ),
                random = random
            )

            assertEquals("Item B", rangeGroup.getItemValue())
            assertEquals("Item C", rangeGroup.getItemValue())
            assertEquals("Item A", rangeGroup.getItemValue())
            assertEquals("Item D", rangeGroup.getItemValue())
            assertEquals("Item B", rangeGroup.getItemValue())
        }
    }

    @Nested
    @DisplayName("Rare Chance values")
    inner class ChanceValueTest {

        @Test
        fun `If some chances are less than zero, only use items with a positive chance and get a random value`() {
            val rangeGroup = RangeGroup(
                listOf(
                    RangeValue("Item A", -5.0),
                    RangeValue("Item B", 45.0),
                    RangeValue("Item C", chance = -50.0),
                )
            )

            assertEquals("Item B", rangeGroup.getItemValue())
        }

        @Test
        fun `If chances are all less than zero, return null`() {
            val rangeGroup = RangeGroup(
                listOf(
                    RangeValue("Item A", -5.0),
                    RangeValue("Item B", -45.0),
                    RangeValue("Item C", chance = -50.0),
                )
            )

            assertNull(rangeGroup.getItemValue())
        }

        @Test
        fun `If all chances are null, return a random item`() {
            val random = DoubleListRandom(listOf(55.0, 75.5, 22.3, 10.0, 38.2))

            val rangeGroup = RangeGroup(
                listOf(
                    RangeValue("Item A"),
                    RangeValue("Item B", null),
                    RangeValue("Item C", chance = null),
                    RangeValue("Item D"),
                ),
                random = random
            )

            assertTrue {
                rangeGroup.settleChances().items.all { (it.chance ?: 0.0) == 25.0 }
            }

            assertEquals("Item C", rangeGroup.getItemValue())
            assertEquals("Item D", rangeGroup.getItemValue())
            assertEquals("Item A", rangeGroup.getItemValue())
            assertEquals("Item A", rangeGroup.getItemValue())
            assertEquals("Item B", rangeGroup.getItemValue())
        }

        @Test
        fun `If an empty list is sent into a RangeGroup, return null`() {
            val rangeGroup = RangeGroup<String>(emptyList(), 55.0)

            assertNull(rangeGroup.getItemValue())
        }

        @Test
        fun `If an empty list with a null chance is sent into a RangeGroup, return null`() {
            val rangeGroup = RangeGroup<String>(emptyList())

            assertNull(rangeGroup.getItemValue())
        }
    }

    @Nested
    @DisplayName("Utils")
    inner class UtilTest {

        @Test
        fun `A RangeGroup from a Map converts successfully`() {
            val rangeGroupFromMap = RangeGroup.fromMap(
                mapOf(
                    "test value A" to 23.0,
                    "test value B" to 17.0,
                    "test value C" to 26.0,
                )
            )

            val rangeGroup = RangeGroup(
                listOf(
                    RangeValue("test value A", 23.0),
                    RangeValue("test value B", 17.0),
                    RangeValue("test value C", 26.0),
                )
            )

            assertEquals(rangeGroup, rangeGroupFromMap)
        }

        @Test
        fun `toString() is easier to see and doesn't show the random class`() {
            val testRangeValues = listOf(
                RangeValue("test value A"),
                RangeValue("test value B"),
                RangeValue("test value C"),
            )
            val testChance = 42.32
            val testRangeGroup = RangeGroup(testRangeValues, testChance)

            val testStringOutput = "RangeGroup(chance=$testChance, items=$testRangeValues)"
            assertEquals(testStringOutput, testRangeGroup.toString())
        }
    }
}