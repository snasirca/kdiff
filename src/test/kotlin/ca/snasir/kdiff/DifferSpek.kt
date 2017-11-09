package ca.snasir.kdiff

import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object DifferSpek: Spek({
    describe("a calculator") {
        on("addition") {
            it("should return the result of adding the first number to the second number") {
                val calculator = Differ()

                val sum = calculator.sum(2, 4)

                expect(sum).to.equal(6)
            }
        }

        on("subtraction") {
            it("should return the result of subtracting the second number from the first number") {
                val calculator = Differ()

                val subtract = calculator.subtract(4, 2)

                expect(subtract).to.equal(2)
            }
        }
    }
})
