package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@DisplayName("Win Condition should")
class WinConditionTest {
    @Test
    @DisplayName("check if 4 of the same coins are in a horizontal position")
    fun horizontalWin() {
        val winCondition = WinCondition()
        val coin = Coin.Yellow
        val startColumn = 2

        var board = Board.empty()
        var position =
            Position(
                row = 1,
                column = startColumn,
                coin = coin,
            )
        for (column in startColumn..6) {
            position =
                Position(
                    row = 1,
                    column = column,
                    coin = coin,
                )
            board = board.placeCoinAt(position)
        }

        assertTrue(winCondition.checkHorizontalWin(board, position))
    }

    @Test
    @DisplayName("check if horizontal win condition is not met when there are not 4 of the same coins in the row")
    fun horizontalWinNotMet() {
        val winCondition = WinCondition()
        val startColumn = 1

        var board = Board.empty()
        board = board.placeCoinAt(Position(row = 2, column = startColumn, coin = Coin.Yellow))
        board = board.placeCoinAt(Position(row = 2, column = startColumn + 1, coin = Coin.Red))
        board = board.placeCoinAt(Position(row = 2, column = startColumn + 3, coin = Coin.Yellow))
        board = board.placeCoinAt(Position(row = 2, column = startColumn + 4, coin = Coin.Yellow))
        val lastPosition = Position(row = 2, column = startColumn + 5, coin = Coin.Yellow)

        assertFalse(winCondition.checkHorizontalWin(board, lastPosition))
    }
}
