package dev.mfazio.utils.math

fun List<Int>.product() = this.reduce { acc, i -> acc * i }

fun List<Long>.product() = this.reduce { acc, i -> acc * i }