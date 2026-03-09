package com.appsoluut.connect4

class Connect4 {
    private val renderer: Renderer
    private var board: Board

    private constructor(
        renderer: Renderer,
        board: Board,
    ) {
        this.renderer = renderer
        this.board = board
    }

    companion object {
        fun newGame(renderer: Renderer = defaultRenderer()): Connect4 = Connect4(renderer, Board.empty())

        fun defaultRenderer(): Renderer = TextRenderer()

        @JvmStatic
        fun main(args: Array<String>) {
            val game = newGame()
            val input = TerminalInput()
            while (true) {
                println(game.renderer.renderBoard(game.board))
                print("Input column: ")
                input.readColumn()?.let { column ->
                    val position =
                        Position(
                            column = column,
                            row = 1,
                            coin = Coin.Yellow,
                        )
                    val board = game.board.placeCoinAt(position)
                    game.updateBoard(board)
                }
            }
        }
    }

    fun updateBoard(board: Board) {
        this.board = board
    }
}
