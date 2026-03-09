package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

@DisplayName("Output should")
class OutputTest {
    @Test
    @DisplayName("contain an error when invalid column was selected")
    fun outputToTerminal() {
        val input = FakeInput()
        val output = FakeOutput()
        val game =
            Connect4.newGame(
                input = input,
                output = output,
            )

        game.runGameLoop(2)

        assertTrue(output.buffer.contains("[ERROR]", ignoreCase = true), "Output was:\n\n${output.buffer}")
    }

    private class FakeInput : Input {
        override fun readColumn(): Int? = 0
    }

    private class FakeOutput : Output {
        var buffer: String = ""

        override fun clear() {
            buffer = ""
        }

        override fun print(output: String) {
            buffer += output
        }

        override fun println(output: String) {
            buffer += "$output\n"
        }
    }
}
