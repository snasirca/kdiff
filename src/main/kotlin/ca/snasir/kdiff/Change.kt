package ca.snasir.kdiff

data class Change<out DiffKey, DiffValue>(
    val key: DiffKey,
    var oldValue: DiffValue,
    var newValue: DiffValue
) {
    fun isAChange(): Boolean {
        return oldValue != newValue
    }
}
