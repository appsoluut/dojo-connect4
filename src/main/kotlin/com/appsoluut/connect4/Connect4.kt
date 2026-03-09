package com.appsoluut.connect4

class Connect4 private constructor(
    val renderer: Renderer,
    val board: Board,
) {
    companion object {
        fun newGame(renderer: Renderer = defaultRenderer()): Connect4 = Connect4(renderer, Board.empty())

        fun defaultRenderer(): Renderer = TextRenderer()

        @JvmStatic
        fun main(args: Array<String>) {
            val game = newGame()
            println(game.renderer.renderBoard(game.board))
        }
    }
}
