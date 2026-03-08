package com.appsoluut.connect4

class TextRenderer : Renderer {
    override fun renderCoin(coin: Coin?): String =
        when (coin) {
            Coin.Yellow -> "\uD83D\uDFE1"
            Coin.Blue -> "\uD83D\uDD34"
            else -> "◯"
        }

    override fun renderBoard(board: Board): String {
        val output = StringBuilder()
        val boardColumns =
            (1..board.columns)
                .joinToString(" ") { it.toString().padStart(3, ' ') }
                .padStart(board.columns * 3 - 1, ' ')

        output.append(boardColumns).append('\n')
        for (row in board.rows downTo 1) {
            output.append(row)
            output.append(' ')
            repeat(board.columns) {
                output.append(Coin.Yellow)
            }
            output.append('\n')
        }

        return output.toString()
    }
}
