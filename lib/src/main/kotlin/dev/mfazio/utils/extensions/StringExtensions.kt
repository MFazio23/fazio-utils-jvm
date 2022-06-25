package dev.mfazio.utils.extensions

fun String?.isNotNullOrEmpty() = !this.isNullOrEmpty()
fun String?.isNotNullOrBlank() = !this.isNullOrBlank()

fun CharSequence?.indexOfFirstOrNull(predicate: (Char) -> Boolean) = this?.indexOfFirst(predicate)?.let {
    if (it < 0) null else it
}

fun CharSequence?.indexOfLastOrNull(predicate: (Char) -> Boolean) = this?.indexOfLast(predicate)?.let {
    if (it < 0) null else it
}