package ca.snasir.kdiff

data class Change<out ElementKey, ElementValue>(
    val elementKey: ElementKey,
    var oldValue: ElementValue? = null,
    var newValue: ElementValue? = null
) {
    fun isAChange(): Boolean {
        return oldValue != newValue
    }
}
