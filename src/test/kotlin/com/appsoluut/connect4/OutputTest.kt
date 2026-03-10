package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

@DisplayName("Output should")
class OutputTest {
    @Test
    @DisplayName("contain a coin in column 4 when input is accepted")
    fun containCoinInColumnAfterInput() {
        val input = TerminalInput({ "4" })
        val output = FakeOutput()
        val renderer = TextRenderer()
        val game =
            Connect4.newGame(
                input = input,
                output = output,
            )
        val board = Board.empty()
        val emptyBoard = renderer.renderBoard(board)

        game.runGameLoop(1)
        assertTrue(output.buffer.contains(emptyBoard))

        val boardWithCoin =
            board.placeCoinAt(
                Position(
                    column = 4,
                    row = 1,
                    coin = Coin.Yellow,
                ),
            )
        val boardWithCoinInColumn = renderer.renderBoard(boardWithCoin)

        game.runGameLoop(1)
        assertTrue(
            output.buffer.contains(boardWithCoinInColumn),
            "Board doesn't contain coin:\n${output.buffer}\n\nvs:\n$boardWithCoinInColumn",
        )
    }

    @Test
    @DisplayName("contain an error when invalid column was selected")
    fun displayErrorForInvalidColumn() {
        val input = TerminalInput({ "0" })
        val output = FakeOutput()
        val game =
            Connect4.newGame(
                input = input,
                output = output,
            )

        game.runGameLoop(2)

        assertTrue(output.buffer.contains("Column 0 is out of range", ignoreCase = true), "Output was:\n\n${output.buffer}")
    }

    @Test
    @DisplayName("contain an error when full column was selected")
    fun displayErrorForFullColumn() {
        val column = 3
        val input = TerminalInput({ "$column" })
        val output = FakeOutput()
        val game =
            Connect4.newGame(
                input = input,
                output = output,
            )

        game.runGameLoop(1) // Skip intro
        game.runGameLoop(Board.ROWS + 1)

        assertTrue(output.buffer.contains("Column $column is full", ignoreCase = true), "Output was:\n\n${output.buffer}")
    }
}
