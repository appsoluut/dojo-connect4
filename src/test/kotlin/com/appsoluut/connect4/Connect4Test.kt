package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Connect4")
class Connect4Test {
    @Test
    @DisplayName("should create a empty board with 6 rows and 7 columns")
    fun testExample() {
        val game = Connect4()
        assertEquals(6, game.getBoard().rows)
        assertEquals(7, game.getBoard().columns)
    }
}