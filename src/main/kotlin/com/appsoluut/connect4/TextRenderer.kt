package com.appsoluut.connect4

class TextRenderer : Renderer {
    override fun renderCoin(coin: Coin?): String =
        when (coin) {
            Coin.Yellow -> "\uD83D\uDFE1"
            Coin.Blue -> "\uD83D\uDD34"
            else -> "◯"
        }

    override fun renderBoard(board: Board): String {
        val boardColumns =
            (1..board.columns)
                .joinToString(" ") { it.toString().padStart(3, ' ') }
                .padStart(board.columns * 3 - 1, ' ')

        return boardColumns
    }
}
