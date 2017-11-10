package ca.snasir.kdiff

import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*

object DifferSpek : Spek({
    data class Data(val key: Int, val value: String)

    val nullData = ""

    describe("the differ") {
        it("diffs an old and new collection of objects") {
            val oldCollection = listOf(Data(1, "a"), Data(2, "b"))
            val newCollection = listOf(Data(2, "c"), Data(3, "d"))
            val differ = Differ<Data, Int, String>({ it.key }, { it.value }, nullData)

            val diffs = differ.diffChanges(oldCollection, newCollection)

            expect(diffs).to.have.size(3)
            expect(diffs.find { it.key == 1 }?.oldValue).to.equal("a")
            expect(diffs.find { it.key == 2 }?.oldValue).to.equal("b")
            expect(diffs.find { it.key == 2 }?.newValue).to.equal("c")
            expect(diffs.find { it.key == 3 }?.newValue).to.equal("d")
        }

        it("ignores unchanged values in the diff") {
            val oldCollection = listOf(Data(1, "a"))
            val newCollection = listOf(Data(1, "a"))
            val differ = Differ<Data, Int, String>({ it.key }, { it.value }, nullData)

            val diffs = differ.diffChanges(oldCollection, newCollection)

            expect(diffs).to.be.empty()
        }
    }
})
