package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import kotlin.test.assertEquals

@DisplayName("Renderer should")
class RendererTest {
    @ParameterizedTest(name = "{0}")
    @EnumSource(Coin::class)
    @DisplayName("display coins visibly distinct from other player's coins")
    fun distinctCoinsTest(coin: Coin?) {
        val renderer = TextRenderer()

        val expectedCoin =
            when (coin) {
                Coin.Yellow -> "\uD83D\uDFE1"
                Coin.Blue -> "\uD83D\uDD34"
                null -> "◯"
            }

        assertEquals(expectedCoin, renderer.renderCoin(coin))
    }
}
