package com.appsoluut.connect4

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.CsvSource
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DisplayName("Board should")
class BoardTest {
    @Test
    @DisplayName("have 6 rows")
    fun has6Rows() {
        val board = Board.empty()
        assertEquals(Board.ROWS, board.rows)
    }

    @Test
    @DisplayName("have 7 columns")
    fun has7Columns() {
        val board = Board.empty()
        assertEquals(Board.COLUMNS, board.columns)
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

    @Test
    @DisplayName("have a non-empty board when a coin is occupying a space")
    fun checkIfBoardIsNotEmptyWhenACoinOccupiesTheBoard() {
        val board = Board.empty()
        val updatedBoard =
            board.placeCoinAt(
                position =
                    Position(
                        row = 1,
                        column = 1,
                        coin = Coin.Yellow,
                    ),
            )

        assertFalse(updatedBoard.isEmpty())
    }

    @Test
    @DisplayName("have an unavailable space when a coin is already occupying the position")
    fun haveOccupiedSpaceAtPosition() {
        val board = Board.empty()
        val updatedBoard =
            board.placeCoinAt(
                position =
                    Position(
                        row = 1,
                        column = 1,
                        coin = Coin.Yellow,
                    ),
            )

        assertFalse(updatedBoard.isPositionAvailableAt(row = 1, column = 1))
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

    @ParameterizedTest(name = "{index} => contain {0} coin in column {2}, row {1}")
    @ArgumentsSource(BoardPlayerPositionsArgumentsProvider::class)
    @DisplayName("contain specific player coin in a position")
    fun boardContainsCoinAt(
        coin: Coin,
        row: Int,
        column: Int,
    ) {
        val board = Board.empty()
        val expectedPosition =
            Position(
                row = row,
                column = column,
                coin = coin,
            )

        val updatedBoard =
            board.placeCoinAt(
                position =
                    Position(
                        row = row,
                        column = column,
                        coin = coin,
                    ),
            )
        val position = updatedBoard.positionAt(row = expectedPosition.row, column = expectedPosition.column)

        assertEquals(expectedPosition, position)
    }

    private class BoardPlayerPositionsArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments?>? {
            return Stream.of(
                Arguments.of(Coin.Yellow, 1, 1),
                Arguments.of(Coin.Blue, 1, 2),
            )
        }
    }
}
