package ca.snasir.kdiff

typealias Changes<Key, Value> = Collection<Change<Key, Value>>

class Differ<in Element, out Key, Value>(
    private val keyValuePairFor: (it: Element) -> Pair<Key, Value>
) {
    private val changes = HashMap<Key, Change<Key, Value>>()

    fun diffChanges(oldElements: Collection<Element>, newElements: Collection<Element>): Changes<Key, Value> {
        oldElements.forEach {
            val keyValuePair = keyValuePairFor(it)
            getOrStartTrackingChange(keyValuePair.first).oldValue = keyValuePair.second
        }
        newElements.forEach {
            val keyValuePair = keyValuePairFor(it)
            getOrStartTrackingChange(keyValuePair.first).newValue = keyValuePair.second
        }

        return changes.values.filter { it.isAChange() }
    }

    private fun getOrStartTrackingChange(key: Key): Change<Key, Value> {
        return changes.getOrPut(key, { Change(key) })
    }
}
