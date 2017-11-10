package ca.snasir.kdiff

class Differ<in T, out DiffKey, DiffValue>(
    private val keyGetter: (it: T) -> DiffKey,
    private val valueGetter: (it: T) -> DiffValue,
    private val nullValue: DiffValue
) {
    private val changes = HashMap<DiffKey, Change<DiffKey, DiffValue>>()

    fun diffChanges(oldCollection: Collection<T>, newCollection: Collection<T>): Collection<Change<DiffKey, DiffValue>> {
        oldCollection.forEach {
            val key = keyGetter(it)
            val value = valueGetter(it)
            if (changes.containsKey(key)) {
                changes[key]?.oldValue = value
            } else {
                changes.put(key, Change(key, oldValue = value, newValue = nullValue))
            }
        }
        newCollection.forEach {
            val key = keyGetter(it)
            val value = valueGetter(it)
            if (changes.containsKey(key)) {
                changes[key]?.newValue = value
            } else {
                changes.put(key, Change(key, oldValue = nullValue, newValue = value))
            }
        }

        return changes.values.filter { it.isAChange() }
    }
}
