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
        board = board.placeCoinAt(lastPosition)

        assertFalse(winCondition.checkHorizontalWin(board, lastPosition))
    }

    @Test
    @DisplayName("check if 4 of the same coins are in a vertical position")
    fun verticalWin() {
        val winCondition = WinCondition()
        val coin = Coin.Red
        val column = 3

        var board = Board.empty()
        var position =
            Position(
                row = 1,
                column = column,
                coin = coin,
            )
        for (row in 1..4) {
            position =
                Position(
                    row = row,
                    column = column,
                    coin = coin,
                )
            board = board.placeCoinAt(position)
        }

        assertTrue(winCondition.checkVerticalWin(board, position))
    }

    @Test
    @DisplayName("check if vertical win condition is not met when there are not 4 of the same coins in the same column")
    fun verticalWinNotMet() {
        val winCondition = WinCondition()

        var board = Board.empty()
        board = board.placeCoinAt(Position(row = 1, column = 2, coin = Coin.Yellow))
        board = board.placeCoinAt(Position(row = 2, column = 2, coin = Coin.Red))
        board = board.placeCoinAt(Position(row = 3, column = 2, coin = Coin.Yellow))
        board = board.placeCoinAt(Position(row = 4, column = 2, coin = Coin.Yellow))
        val lastPosition = Position(row = 5, column = 2, coin = Coin.Yellow)
        board = board.placeCoinAt(lastPosition)

        assertFalse(winCondition.checkVerticalWin(board, lastPosition))
    }
}
