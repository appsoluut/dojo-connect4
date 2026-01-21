package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DisplayName("Connect4 should")
class Connect4Test {
    @Test
    @DisplayName("create a empty board with 6 rows and 7 columns")
    fun emptyBoardWith6RowsAnd7Columns() {
        assertEquals(6, Board.BOARD_ROWS, "Board should have 6 rows")
        assertEquals(7, Board.BOARD_COLUMNS, "Board should have 7 columns")
    }

    @Test
    @DisplayName("create a empty board and all fields are empty")
    fun emptyBoard() {
        val game = Connect4()
        assertTrue(game.isEmptyBoard(), "All board fields should be empty")
    }
}
