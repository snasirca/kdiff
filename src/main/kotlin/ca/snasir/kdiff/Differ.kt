package ca.snasir.kdiff

class Differ<in Element, out Key, Value>(
    private val keyValuePairFor: (it: Element) -> KeyValuePair<Key, Value>
) {
    private val changes = HashMap<Key, Change<Key, Value>>()

    fun diffChanges(oldElements: Collection<Element>, newElements: Collection<Element>): Changes<Key, Value> {
        oldElements.map(keyValuePairFor).forEach { getOrStartTrackingChange(it.key).oldValue = it.value }
        newElements.map(keyValuePairFor).forEach { getOrStartTrackingChange(it.key).newValue = it.value }

        return changes.values.filter { it.isAChange() }
    }

    private fun getOrStartTrackingChange(key: Key): Change<Key, Value> {
        return changes.getOrPut(key, { Change(key) })
    }
}
