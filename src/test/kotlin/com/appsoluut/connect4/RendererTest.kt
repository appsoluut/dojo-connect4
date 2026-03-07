package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@DisplayName("Renderer should")
class RendererTest {
    @ParameterizedTest(name = "Player {0} => Coin {1}")
    @ArgumentsSource(CoinRendererArgumentsProvider::class)
    @DisplayName("display coins visibly distinct from other player's coins")
    fun distinctCoinsTest(
        player: Int?,
        coin: Coin?,
    ) {
        val renderer = TextRenderer()

        val expectedCoin =
            when (coin) {
                Coin.Yellow -> "\uD83D\uDFE1"
                Coin.Blue -> "\uD83D\uDD34"
                null -> "◯"
            }

        assertEquals(expectedCoin, renderer.renderCoin(coin))
    }

    private class CoinRendererArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments?>? {
            return Stream.of(
                Arguments.of(null, null),
                Arguments.of(1, Coin.Yellow),
                Arguments.of(2, Coin.Blue),
            )
        }
    }
}
