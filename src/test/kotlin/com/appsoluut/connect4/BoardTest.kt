package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DisplayName("Board should")
class BoardTest {
    @Test
    @DisplayName("have 6 rows")
    fun has6Rows() {
        val board = Board.empty()
        assertEquals(6, board.rows)
    }

    @Test
    @DisplayName("have 7 columns")
    fun has7Columns() {
        val board = Board.empty()
        assertEquals(7, board.columns)
    }

    @Test
    @DisplayName("have all positions available for coin placement")
    fun areAllPositionsAvailableForCoin() {
        val board = Board.empty()
        for (row in 0 until board.rows) {
            for (column in 0 until board.columns) {
                assertTrue(board.isPositionAvailableAt(row = row, column = column))
            }
        }
    }

    @Test
    @DisplayName("have all positions empty when new game starts")
    fun hasAllPositionsEmpty() {
        val board = Board.empty()
        assertTrue(board.isEmpty())
    }

    @ParameterizedTest
    @CsvSource(
        "0,0",
        "1,2",
    )
    @DisplayName("have all positions know their row and column")
    fun allPositionsReferenceRowAndColumn(
        row: Int,
        column: Int,
    ) {
        val board = Board.empty()

        val position = board.positionAt(row = row, column = column)

        assertEquals(row, position.row)
        assertEquals(column, position.column)
    }

    @Test
    @DisplayName("contain player 1's coin in column 1, row 1")
    fun boardContainsPlayer1CoinAt1_1() {
        val board = Board.empty()
        val expectedPosition =
            Position(
                row = 1,
                column = 1,
                coin = Coin.Yellow,
            )

        val updatedBoard = board.placeCoinAt(
            position =
                Position(
                    row = 1,
                    column = 1,
                    coin = Coin.Yellow,
                ),
        )
        val position = updatedBoard.positionAt(row = expectedPosition.row, column = expectedPosition.column)

        assertEquals(expectedPosition, position)
    }
}
