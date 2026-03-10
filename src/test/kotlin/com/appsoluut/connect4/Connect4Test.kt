package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@DisplayName("Connect4 should")
class Connect4Test {
    @Test
    @DisplayName("create a new board when new game starts")
    fun emptyBoardOnNewGame() {
        val game = Connect4.newGame()
        assertNotNull(game)
    }

    @Test
    @DisplayName("show intro and rules text before the game starts")
    fun showIntroBeforeGameStarts() {
        val output = FakeOutput()
        val renderer = TextRenderer()
        val game =
            Connect4.newGame(
                input = DummyInput(),
                output = output,
            )

        game.intro()

        assertTrue(output.buffer.contains(renderer.renderInstructions(Board.empty())))
    }

    private class DummyInput : Input {
        override fun readln(): String? = null

        override fun readColumn(): Int? = null
    }
}
