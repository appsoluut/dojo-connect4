package com.appsoluut.connect4

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
        var lastError: String? = null
        var iterations = 0
        while (maxIterations == INFINITE_ITERATIONS || iterations < maxIterations) {
            val currentPlayer = turn.getCurrentPlayer()

            output.clear()

            output.println(renderer.renderBoard(board))

            lastError?.let { error ->
                output.println("\n>> $error <<\nTry again!\n")
            }
            lastError = null

            output.println(renderer.renderPlayerTurn(currentPlayer))

            output.print("Input column: ")
            input.readColumn()?.let { column ->
                val validator = Validator(board)
                if (!validator.isColumnValid(column)) {
                    lastError = "[ERROR] Column $column is out of range [1-${board.columns}]!"
                    return@let
                }

                val board =
                    board.dropCoinIn(currentPlayer.coin(), column).getOrElse {
                        lastError = "[ERROR] ${it.message}"
                        return@let
                    }
                updateBoard(board)
                turn.advance()
            }
            iterations++
        }
    }

    private fun updateBoard(board: Board) {
        this.board = board
    }
}
