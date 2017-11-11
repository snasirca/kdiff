# kdiff

[ ![Download](https://api.bintray.com/packages/snasirca/kotlin/kdiff/images/download.svg) ](https://bintray.com/snasirca/kotlin/kdiff/_latestVersion)

Simple *O(n)* diffing library

This is a simple Kotlin library I built to try out the following tech stack:
- Kotlin (main language)
- Gradle - Kotlin DSL (build system)
- Spek ([BDD](http://joshldavis.com/2013/05/27/difference-between-tdd-and-bdd/) style unit testing framework)
- Expekt (expectation style assertions)

The differ will take two collections, an old and new, and a lambda for extracting a `Pair` that:
1. Represents a _key_ for determining which values in the collections are for the same data element / field
2. Represents the _value_ for each element in the collection

## Example Usage:

```kotlin
import ca.snasir.kdiff

// Input dataset
val oldCollection = listOf(Element(1, "a"), Element(2, "b"))
val newCollection = listOf(Element(2, "c"), Element(3, "d"))

// Instantiate the differ with a key-value extraction lambda
val differ = Differ({ it: Element -> KeyValuePair(it.key, it.value) })

// Run differ in O(n) time
val diffs = differ.diffChanges(oldCollection, newCollection)
```

The resulting diffs will be of type `Changes<Int, String>`.
