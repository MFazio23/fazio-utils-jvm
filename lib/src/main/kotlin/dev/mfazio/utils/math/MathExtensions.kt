package dev.mfazio.utils.math

fun Collection<Int>.product() = this.reduce { acc, i -> acc * i }

fun Collection<Long>.product() = this.reduce { acc, i -> acc * i }