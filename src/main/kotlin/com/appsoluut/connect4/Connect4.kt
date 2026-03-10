package com.appsoluut.connect4

import com.appsoluut.connect4.win.WinCondition

class Connect4 private constructor(
    private val turn: Turn,
    private val renderer: Renderer,
    private val input: Input,
    private val output: Output,
    private var board: Board,
) {
    companion object {
        const val INFINITE_ITERATIONS = -1

        fun newGame(
            turn: Turn = Turn(),
            renderer: Renderer = TextRenderer(),
            input: Input = TerminalInput(),
            output: Output = TerminalOutput(),
        ): Connect4 =
            Connect4(
                turn = turn,
                renderer = renderer,
                input = input,
                output = output,
                board = Board.empty(),
            )

        @JvmStatic
        fun main(args: Array<String>) {
            val game = newGame()
            if ("q".equals(game.intro(), ignoreCase = true)) {
                return
            }
            game.runGameLoop()
        }
    }

    fun intro(): String? {
        output.clear()
        output.println(renderer.renderInstructions(board))
        return input.readln()
    }

    fun runGameLoop(maxIterations: Int = INFINITE_ITERATIONS) {
        var message: String? = null
        var iterations = 0
        val winCondition = WinCondition()
        while (maxIterations == INFINITE_ITERATIONS || iterations < maxIterations) {
            val currentPlayer = turn.getCurrentPlayer()

            output.clear()

            output.println(renderer.renderBoard(board))

            message?.let { error ->
                output.println("\n>> $error <<\nTry again!\n")
            }
            message = null

            output.println(renderer.renderPlayerTurn(currentPlayer))

            output.print("Input column: ")
            input.readColumn()?.let { column ->
                val validator = Validator(board)
                if (!validator.isColumnValid(column)) {
                    message = "[ERROR] Column $column is out of range [1-${board.columns}]!"
                    return@let
                }

                val moveResult =
                    board.dropCoinIn(currentPlayer.coin(), column).getOrElse {
                        message = "[ERROR] ${it.message}"
                        return@let
                    }

                updateBoard(moveResult.board)

                if (winCondition.check(moveResult.board, moveResult.position)) {
                    message = "Player ${currentPlayer.name} wins!"
                    return@let
                }

                turn.advance()
            }
            iterations++
        }
    }

    private fun updateBoard(board: Board) {
        this.board = board
    }
}
