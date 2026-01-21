package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
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
    @DisplayName("have all 42 available for coin placement")
    fun have42Positions() {
        val board = Board.empty()
        for (row in 0 until board.rows) {
            for (column in 0 until board.columns) {
                assertDoesNotThrow {
                    board.positionAt(row, column)
                }
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
}
