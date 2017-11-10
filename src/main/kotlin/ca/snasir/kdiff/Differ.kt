package ca.snasir.kdiff

typealias Changes<Key, Value> = Collection<Change<Key, Value>>

class Differ<in T, out Key, Value>(
    private val keyFor: (it: T) -> Key,
    private val valueFor: (it: T) -> Value,
    private val nullValue: Value
) {
    private val changes = HashMap<Key, Change<Key, Value>>()

    fun diffChanges(oldCollection: Collection<T>, newCollection: Collection<T>): Changes<Key, Value> {
        oldCollection.forEach {
            getOrStartTrackingChange(keyFor(it)).oldValue = valueFor(it)
        }
        newCollection.forEach {
            getOrStartTrackingChange(keyFor(it)).newValue = valueFor(it)
        }

        return changes.values.filter { it.isAChange() }
    }

    private fun getOrStartTrackingChange(key: Key): Change<Key, Value> {
        return changes.getOrPut(key, { Change(key, oldValue = nullValue, newValue = nullValue) })
    }
}
