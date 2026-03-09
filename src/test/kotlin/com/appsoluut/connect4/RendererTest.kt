package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DisplayName("Renderer should")
class RendererTest {
    @ParameterizedTest(name = "Player {0} => Coin {1}")
    @CsvSource(
        value = [
            "1, Yellow",
            "2, Blue",
            "NoPlayer, None",
        ],
        nullValues = ["NoPlayer"],
    )
    @DisplayName("display coins visibly distinct from other player's coins")
    fun distinctCoinsTest(
        player: Int?,
        coin: Coin,
    ) {
        val renderer = TextRenderer()

        val playerCoinMap =
            mapOf(
                1 to Coin.Yellow,
                2 to Coin.Blue,
                null to Coin.None,
            )

        val expectedCoin =
            playerCoinMap[player]?.let { coin ->
                when (coin) {
                    Coin.Yellow -> "\uD83D\uDFE1"
                    Coin.Blue -> "\uD83D\uDD34"
                    Coin.None -> "\u2B55"
                }
            }

        assertEquals(expectedCoin, renderer.renderCoin(coin))
    }

    @Test
    @DisplayName("show board with column numbers 1 to 7")
    fun columnNumbers1To7Test() {
        val renderer = TextRenderer()
        val expectedColumns = (1..Board.COLUMNS).toList()

        val actualRendering = renderer.renderBoard(Board.empty()).trim()
        val allNumbersPresent = expectedColumns.all { actualRendering.contains(it.toString()) }

        assertTrue(allNumbersPresent)
    }

    @Test
    @DisplayName("show board with row numbers 6 to 1 (top to bottom)")
    fun rowNumbers6To1Test() {
        val renderer = TextRenderer()
        val regex = "^\\s*(\\d+)".toRegex()
        val expectedRows = (1..Board.ROWS).reversed().toList()

        val output = renderer.renderBoard(Board.empty()).lines()
        val actualRows =
            output.drop(1).mapNotNull { line ->
                regex.find(line)?.groupValues?.get(1)?.toIntOrNull()
            }

        assertEquals(expectedRows, actualRows)
    }
}
