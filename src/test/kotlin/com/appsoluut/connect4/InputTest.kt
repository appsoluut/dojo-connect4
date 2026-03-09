package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@DisplayName("Input should")
class InputTest {
    @Test
    @DisplayName("accept and validate column 4 from input if in range")
    fun validateValidColumn() {
        val validator = Validator(Board.empty())

        val input = StubTerminalInput().readColumn()

        assertNotNull(input)
        assertTrue(validator.isColumnValid(input))
    }

    private class StubTerminalInput : Input {
        override fun readColumn(): Int? = 4
    }
}
