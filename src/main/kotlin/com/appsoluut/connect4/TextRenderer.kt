package com.appsoluut.connect4

class TextRenderer : Renderer {
    override fun renderCoin(coin: Coin): String =
        when (coin) {
            Coin.Yellow -> "\uD83D\uDFE1"
            Coin.Red -> "\uD83D\uDD34"
            Coin.None -> "\u2B55"
        }

    override fun renderBoard(board: Board): String =
        buildString {
            append(generateColumnsHeader(board)).append('\n')
            append(generateBoardWithRowNumbers(board)).append('\n')
        }.trimEnd()

    override fun renderInstructions(board: Board): String =
        """
        Welcome to Connect 4!

        Rules of the game:
        * The board is ${board.columns} columns wide, and ${board.rows} rows high
        * When a coin is inserted, it will fall to the lowest possible space in that column
        * Player 1 will start, then the game will alternate players each round
        
        Winning conditions:
        * When 4 of the same coins are horizontal
        * When 4 of the same coins are vertical
        * Or when 4 of the same coins are diagonal
        
        A draw will occur when the board is full and no win condition has been met.
        
        - Press ENTER to start the game or 'q' to quit -
        """.trimIndent()

    private fun generateColumnsHeader(board: Board): String {
        val header =
            (1..board.columns)
                .joinToString("/") { it.toString().plus(".").prependIndent(" ").padEnd(4, ' ') }
                .padStart(board.columns * 3 - 1, ' ')
                .prependIndent("    /")
        return "$header/"
    }

    private fun generateBoardWithRowNumbers(board: Board): String =
        buildString {
            for (row in board.rows downTo 1) {
                append(' ')
                append(row)
                append(". |")
                for (column in 1..board.columns) {
                    append(" ")
                    append(renderCoin(board.positionAt(row = row, column = column).coin))
                    append(" |")
                }
                append('\n')
            }
        }.trimEnd()
}
