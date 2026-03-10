package com.appsoluut.connect4.win

import com.appsoluut.connect4.Board
import com.appsoluut.connect4.Position

class WinCondition(
    private val strategies: List<WinStrategy> =
        listOf(
            HorizontalWinStrategy(),
            VerticalWinStrategy(),
            DiagonalWinStrategy(),
            DrawWinStrategy(),
        ),
) {
    fun check(
        board: Board,
        position: Position,
    ): GameResult {
        for (strategy in strategies) {
            val result = strategy(board, position)
            if (result != GameResult.Running) {
                return result
            }
        }
        return GameResult.Running
    }

    companion object {
        const val CONSECUTIVE_COINS = 4
    }
}

enum class GameResult {
    Win,
    Draw,
    Running,
}
