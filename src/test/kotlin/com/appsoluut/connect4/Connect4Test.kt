package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertNotNull

@DisplayName("Connect4 should")
class Connect4Test {
    @Test
    @DisplayName("create a empty board when new game starts")
    fun emptyBoardOnNewGame() {
        val game = Connect4()
        assertNotNull(game.board)
    }
}
