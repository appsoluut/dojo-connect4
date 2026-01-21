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
        val board = Board()
        assertEquals(6, board.rows)
    }

    @Test
    @DisplayName("have 7 columns")
    fun has7Columns() {
        val board = Board()
        assertEquals(7, board.columns)
    }

    @Test
    @DisplayName("have 42 positions")
    fun have42Positions() {
        val board = Board()
        assertEquals(42, board.positions.size)
    }

    @Test
    @DisplayName("have all positions empty when new game starts")
    fun hasAllPositionsEmpty() {
        val board = Board()
        assertTrue(board.positions.all { it.isEmpty })
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
        val board = Board()

        val position = board.positionAt(row = row, column = column)

        assertEquals(row, position.row)
        assertEquals(column, position.column)
    }
}
