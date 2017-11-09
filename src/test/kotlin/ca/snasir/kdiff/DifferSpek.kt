package ca.snasir.kdiff

import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object DifferSpek: Spek({
    describe("a calculator") {
        val calculator = Differ()

        on("addition") {
            val sum = calculator.sum(2, 4)

            it("should return the result of adding the first number to the second number") {
                expect(sum).to.equal(6)
            }
        }

        on("subtraction") {
            val subtract = calculator.subtract(4, 2)

            it("should return the result of subtracting the second number from the first number") {
                expect(subtract).to.equal(7)
            }
        }
    }
})
