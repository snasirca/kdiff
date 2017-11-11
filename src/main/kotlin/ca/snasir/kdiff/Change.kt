package ca.snasir.kdiff

typealias Changes<Key, Value> = Collection<Change<Key, Value>>

data class Change<out ElementKey, ElementValue>(
    val elementKey: ElementKey,
    var oldValue: ElementValue? = null,
    var newValue: ElementValue? = null
) {
    fun isAChange(): Boolean {
        return oldValue != newValue
    }
}
