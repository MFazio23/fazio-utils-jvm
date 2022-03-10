package dev.mfazio.utils.random

import kotlin.random.Random

fun rollDie(sides: Int, random: Random = Random.Default) = random.nextInt(sides) + 1
fun rollD4(random: Random = Random.Default) = rollDie(4, random)
fun rollD6(random: Random = Random.Default) = rollDie(6, random)
fun rollD8(random: Random = Random.Default) = rollDie(8, random)
fun rollD10(random: Random = Random.Default) = rollDie(10, random)
fun rollD12(random: Random = Random.Default) = rollDie(12, random)
fun rollD20(random: Random = Random.Default) = rollDie(20, random)
fun rollD100(random: Random = Random.Default) = rollDie(100, random)

fun flipCoin(random: Random = Random.Default) = rollDie(2, random) == 1

fun <T> coinFlip(first: T, second: T, random: Random = Random.Default) =
    if (flipCoin(random)) first else second