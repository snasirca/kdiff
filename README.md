# kdiff

Simple *O(n)* diffing library

This is a simple Kotlin library I built to try out the following tech stack:
- Kotlin (main language)
- Gradle - Kotlin DSL (build system)
- Spek ([BDD](http://joshldavis.com/2013/05/27/difference-between-tdd-and-bdd/) style unit testing framework)
- Expekt (expectation style assertions)

The differ will take two collections, an old and new, and a couple of lambdas that:
1. Specify how to extract a key to know which values in the collections are for the same data element / field
2. Specify how to extract the value for each element in the collection

## Example Usage:

```kotlin
import ca.snasir.kdiff

// Input dataset
val oldCollection = listOf(Data(1, "a"), Data(2, "b"))
val newCollection = listOf(Data(2, "c"), Data(3, "d"))

// Instantiate the differ with the lambdas and null object
val differ = Differ<Data, Int, String>({ it.key }, { it.value }, nullData)

// Run differ in O(n) time
val diffs = differ.diffChanges(oldCollection, newCollection)
```

The resulting diffs will be of type `Changes<Int, String>`.

## Nullability

I'm also trying not to use any nulls inside the library code so upon instantiation of the differ, you have to specify
what the null object for your data payload is. If you want, you can use `null` but that is up to you. You can use the
null object pattern safely.
