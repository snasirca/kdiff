package ca.snasir.kdiff

typealias Changes<Key, Value> = Collection<Change<Key, Value>>

class Differ<in Element, out Key, Value>(
    private val keyFor: (it: Element) -> Key,
    private val valueFor: (it: Element) -> Value,
    private val nullValue: Value
) {
    private val changes = HashMap<Key, Change<Key, Value>>()

    fun diffChanges(oldElements: Collection<Element>, newElements: Collection<Element>): Changes<Key, Value> {
        oldElements.forEach {
            getOrStartTrackingChange(keyFor(it)).oldValue = valueFor(it)
        }
        newElements.forEach {
            getOrStartTrackingChange(keyFor(it)).newValue = valueFor(it)
        }

        return changes.values.filter { it.isAChange() }
    }

    private fun getOrStartTrackingChange(key: Key): Change<Key, Value> {
        return changes.getOrPut(key, { Change(key, oldValue = nullValue, newValue = nullValue) })
    }
}
