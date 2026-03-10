package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Player should")
class PlayerTest {
    @Test
    @DisplayName("show yellow coin for player 1")
    fun yellowCoinFirstPlayer() {
        val player = Player.First

        assertEquals(Coin.Yellow, player.coin())
    }

    @Test
    @DisplayName("show red coin for player 2")
    fun redCoinFirstPlayer() {
        val player = Player.Second

        assertEquals(Coin.Red, player.coin())
    }
}
