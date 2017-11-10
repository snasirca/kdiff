package ca.snasir.kdiff

class Differ<in T, DiffKey, DiffValue>(
    private val keyGetter: (it: T) -> DiffKey,
    private val valueGetter: (it: T) -> DiffValue,
    private val nullValue: DiffValue
) {
    private val changes = HashMap<DiffKey, Change<DiffKey, DiffValue>>()

    fun diffChanges(oldCollection: Collection<T>, newCollection: Collection<T>): Collection<Change<DiffKey, DiffValue>> {
        oldCollection.forEach {
            getChange(keyGetter(it)).oldValue = valueGetter(it)
        }
        newCollection.forEach {
            getChange(keyGetter(it)).newValue = valueGetter(it)
        }

        return changes.values.filter { it.isAChange() }
    }

    private fun getChange(key: DiffKey): Change<DiffKey, DiffValue> {
        return changes.getOrPut(key, { Change(key, oldValue = nullValue, newValue = nullValue) })
    }
}
