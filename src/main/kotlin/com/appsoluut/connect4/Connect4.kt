package com.appsoluut.connect4

import com.appsoluut.connect4.history.FileLogWriter
import com.appsoluut.connect4.history.GameHistory
import com.appsoluut.connect4.history.LogWriter
import com.appsoluut.connect4.win.GameResult
import com.appsoluut.connect4.win.WinCondition
import java.io.File

class Connect4 private constructor(
    private val turn: Turn,
    private val renderer: Renderer,
    private val input: Input,
    private val output: Output,
    private var board: Board,
) {
    private var logged: Boolean = false
    private val logWriter: LogWriter = FileLogWriter(file = File("./logs/history.log"))

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
            var game = newGame()
            if ("q".equals(game.intro(), ignoreCase = true)) {
                return
            }
            var stop: Boolean
            do {
                stop = game.runGameLoop()
                if (!stop) {
                    game = newGame()
                }
            } while (!stop)
        }
    }

    fun intro(): String? {
        output.clear()
        output.println(renderer.renderInstructions(board))
        return input.readln()
    }

    fun runGameLoop(maxIterations: Int = INFINITE_ITERATIONS): Boolean {
        logged = false
        var message: String? = null
        var gameResult: GameResult = GameResult.Running
        var iterations = 0
        val winCondition = WinCondition()
        while (maxIterations == INFINITE_ITERATIONS || iterations < maxIterations) {
            val currentPlayer = turn.getCurrentPlayer()

            output.clear()

            output.println(renderer.renderBoard(board))

            val result =
                when (gameResult) {
                    GameResult.Win -> renderer.renderWin(player = currentPlayer)
                    GameResult.Draw -> renderer.renderDraw()
                    else -> null
                }

            if (gameResult != GameResult.Running) {
                logResult(result)
                displayResult(result)
                output.print("Play again? (yes/no): ")
                val answer = input.readln()?.trim()?.lowercase()
                return when (answer) {
                    "yes" -> false
                    "no" -> true
                    else -> continue
                }
            }

            displayError(message)
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

                gameResult = checkGameCondition(winCondition, moveResult)
            }
            iterations++
        }
        return false
    }

    private fun logResult(result: String?) {
        if (result == null || logged) {
            return
        }

        val history = GameHistory(logWriter = logWriter)
        history.record(result)
        logged = true
    }

    private fun displayResult(result: String?) {
        result?.let { message ->
            output.println(message)
        }
    }

    private fun displayError(message: String?) {
        message?.let { error ->
            output.println("\n>> $error <<\nTry again!\n")
        }
    }

    private fun checkGameCondition(
        winCondition: WinCondition,
        moveResult: MoveResult,
    ): GameResult {
        val gameResult = winCondition.check(moveResult.board, moveResult.position)
        if (gameResult == GameResult.Running) {
            turn.advance()
        }
        return gameResult
    }

    private fun updateBoard(board: Board) {
        this.board = board
    }
}
