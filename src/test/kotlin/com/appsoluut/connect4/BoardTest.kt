package com.appsoluut.connect4

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
        for (row in 1..board.rows) {
            for (column in 1..board.columns) {
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
        "1,1",
        "2,3",
    )
    @DisplayName("have all positions know their row and column")
    fun allPositionsReferenceRowAndColumn(
        row: Int,
        column: Int,
    ) {
        val board = Board.empty()
        val expectedPosition =
            Position(
                row = row,
                column = column,
                coin = Coin.None,
            )

        val actualPosition = board.positionAt(row = row, column = column)

        assertEquals(expectedPosition.row, actualPosition.row)
        assertEquals(expectedPosition.column, actualPosition.column)
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

    @ParameterizedTest(name = "Input column: {0} => Ends up in position: row {1} x column {2}")
    @CsvSource(
        textBlock =
            """# Drop Column, Row   , Column
            1               , 1     , 1
            '1,1'           , 2     , 1
            '1,3,4,4'       , 2     , 4""",
    )
    @DisplayName("drop coin in column and move it as far as possible to the bottom")
    fun dropCoinInColumnAndMove(
        droppedColumn: String,
        positionRow: Int,
        positionColumn: Int,
    ) {
        val inserts = droppedColumn.split(',').mapNotNull { it.trim().toIntOrNull() }

        val board = Board.empty()
        val expectedPosition =
            Position(
                row = positionRow,
                column = positionColumn,
                coin = Coin.Yellow,
            )

        var updatedBoard = board

        inserts.forEach { column ->
            updatedBoard =
                updatedBoard.dropCoinIn(
                    coin = Coin.Yellow,
                    column = column,
                ).getOrThrow()
        }

        assertEquals(expectedPosition, updatedBoard.positionAt(row = positionRow, column = positionColumn))
    }

    @Test
    @DisplayName("reject a coin in a full column and provide error")
    fun dropCoinInFullColumnAndReportError() {
        var board = Board.empty()
        val dropInColumn = 1
        for (row in 1..board.rows) {
            board = board.placeCoinAt(Position(row = row, column = dropInColumn, coin = Coin.Yellow))
        }

        val result = board.dropCoinIn(Coin.Yellow, dropInColumn)

        assertTrue(result.isFailure)
        assertThrows<IllegalStateException> { result.getOrThrow() }
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
