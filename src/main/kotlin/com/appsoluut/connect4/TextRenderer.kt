package com.appsoluut.connect4

class TextRenderer : Renderer {
    override fun renderCoin(coin: Coin?): String =
        when (coin) {
            Coin.Yellow -> "\uD83D\uDFE1"
            Coin.Blue -> "\uD83D\uDD34"
            else -> "⭕"
        }

    override fun renderBoard(board: Board): String {
        val output = StringBuilder()
        output.append(generateColumnsHeader(board)).append('\n')
        output.append(generateBoardWithRowNumbers(board)).append('\n')
        return output.toString().trimEnd()
    }

    private fun generateColumnsHeader(board: Board): String {
        val header =
            (1..board.columns)
                .joinToString(" ") { it.toString().padStart(3, ' ') }
                .padStart(board.columns * 3 - 1, ' ')
        return header
    }

    private fun generateBoardWithRowNumbers(board: Board): String {
        val output = StringBuilder()
        for (row in board.rows downTo 1) {
            output.append(' ')
            output.append(row)
            output.append(". ")
            repeat(board.columns) { index ->
                output.append(renderCoin(Coin.Yellow))
                if (index != board.columns - 1) {
                    output.append(" | ")
                }
            }
            output.append('\n')
        }
        return output.toString().trimEnd()
    }
}
