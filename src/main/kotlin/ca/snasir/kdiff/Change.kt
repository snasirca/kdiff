package ca.snasir.kdiff

data class Change<out DiffKey, DiffValue>(
    val key: DiffKey,
    var oldValue: DiffValue? = null,
    var newValue: DiffValue? = null
) {
    fun isAChange(): Boolean {
        return oldValue != newValue
    }
}
