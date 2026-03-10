package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@DisplayName("Input should")
class InputTest {
    @Test
    @DisplayName("accept and validate column 4 from input if in range")
    fun validateValidColumn() {
        val validator = Validator(Board.empty())

        val input = TerminalInput({ "4" }).readColumn()

        assertNotNull(input)
        assertTrue(validator.isColumnValid(input))
    }

    @Test
    @DisplayName("handle invalid null input")
    fun validateInvalidNullInput() {
        val input = TerminalInput { null }
        assertNull(input.readColumn())
    }
}
