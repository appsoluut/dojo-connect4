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

    @Test
    @DisplayName("show board with column numbers 1 to 7")
    fun columnNumbers1To7Test() {
        val renderer = TextRenderer()
        val expectedColumns = "1   2   3   4   5   6   7"

        val actualRendering = renderer.renderBoard(Board.empty()).trim()

        assertTrue(actualRendering.contains(expectedColumns))
    }

    @Test
    @DisplayName("show board with row numbers 6 to 1 (top to bottom)")
    fun rowNumbers6To1Test() {
        val renderer = TextRenderer()
        val expectedRows = (1..6).reversed().toList()

        val output = renderer.renderBoard(Board.empty()).lines()
        println(output)
        val actualRows =
            output.drop(1).mapNotNull { line ->
                line.trim().split("\\s+".toRegex())[0].toIntOrNull()
            }

        assertEquals(expectedRows, actualRows)
    }
}
