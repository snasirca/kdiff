package ca.snasir.kdiff

typealias Changes<Key, Value> = Collection<Change<Key, Value>>

class Differ<in T, Key, Value>(
    private val keyGetter: (it: T) -> Key,
    private val valueGetter: (it: T) -> Value,
    private val nullValue: Value
) {
    private val changes = HashMap<Key, Change<Key, Value>>()

    fun diffChanges(oldCollection: Collection<T>, newCollection: Collection<T>): Changes<Key, Value> {
        oldCollection.forEach {
            getOrStartTrackingChange(keyGetter(it)).oldValue = valueGetter(it)
        }
        newCollection.forEach {
            getOrStartTrackingChange(keyGetter(it)).newValue = valueGetter(it)
        }

        return changes.values.filter { it.isAChange() }
    }

    private fun getOrStartTrackingChange(key: Key): Change<Key, Value> {
        return changes.getOrPut(key, { Change(key, oldValue = nullValue, newValue = nullValue) })
    }
}
