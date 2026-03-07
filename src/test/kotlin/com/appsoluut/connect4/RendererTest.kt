package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

@DisplayName("Renderer should")
class RendererTest {
    @ParameterizedTest(name = "Player {0} => Coin {1}")
    @CsvSource(
        value = [
            "1, Yellow",
            "2, Blue",
            "null, null",
        ],
        nullValues = ["null"],
    )
    @DisplayName("display coins visibly distinct from other player's coins")
    fun distinctCoinsTest(
        player: Int?,
        coin: Coin?,
    ) {
        val renderer = TextRenderer()

        val playerCoinMap =
            mapOf(
                1 to Coin.Yellow,
                2 to Coin.Blue,
                null to null,
            )

        val expectedCoin =
            when (playerCoinMap[player]) {
                Coin.Yellow -> "\uD83D\uDFE1"
                Coin.Blue -> "\uD83D\uDD34"
                null -> "◯"
            }

        assertEquals(expectedCoin, renderer.renderCoin(coin))
    }
}
