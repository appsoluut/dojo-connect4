package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertTrue

@DisplayName("Turn should")
class TurnTest {
    @Test
    @DisplayName("start with Player 1")
    fun startWithPlayer1() {
        val turn = Turn()
        assertTrue(Player.First == turn.getCurrentPlayer())
    }

    @Test
    @DisplayName("alternate between active players")
    fun alternateActivePlayers() {
        val turn = Turn()

        assertTrue { Player.First == turn.getCurrentPlayer() }
        turn.advance()
        assertTrue { Player.Second == turn.getCurrentPlayer() }
    }
}
