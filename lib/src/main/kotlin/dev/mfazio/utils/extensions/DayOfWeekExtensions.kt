package dev.mfazio.utils.extensions

import java.time.DayOfWeek

fun DayOfWeek.isWeekend() = listOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(this)
fun DayOfWeek.isWeekday() = !this.isWeekend()