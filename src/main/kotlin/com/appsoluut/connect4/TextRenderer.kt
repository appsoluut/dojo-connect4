package com.appsoluut.connect4

class TextRenderer : Renderer {
    override fun renderCoin(coin: Coin?): String =
        when (coin) {
            Coin.Yellow -> "\uD83D\uDFE1"
            Coin.Blue -> "\uD83D\uDD34"
            null -> "◯"
        }
}
