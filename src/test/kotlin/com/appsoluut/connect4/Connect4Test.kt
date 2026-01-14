package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Connect4")
class Connect4Test {
    @Test
    @DisplayName("should create a empty board with 6 rows and 7 columns")
    fun emptyBoardWith6RowsAnd7Columns() {
        val game = Connect4()
        assertEquals(6, game.getBoard().rows)
        assertEquals(7, game.getBoard().columns)
    }

    @Test
    @DisplayName("should create a empty and all fields should be empty")
    fun emptyBoard() {
        val game = Connect4()
        game.isEmptyBoard()
    }
}
