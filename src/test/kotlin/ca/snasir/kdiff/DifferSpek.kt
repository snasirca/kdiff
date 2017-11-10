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

            val diff = differ.diffChanges(oldCollection, newCollection)

            expect(diff).to.have.size(3)
            expect(diff.find { it.key == 1 }?.oldValue).to.equal("a")
            expect(diff.find { it.key == 2 }?.oldValue).to.equal("b")
            expect(diff.find { it.key == 2 }?.newValue).to.equal("c")
            expect(diff.find { it.key == 3 }?.newValue).to.equal("d")
        }
    }
})
