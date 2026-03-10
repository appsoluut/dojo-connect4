package com.appsoluut.connect4.win

import com.appsoluut.connect4.Board
import com.appsoluut.connect4.Position

class WinCondition(
    private val strategies: List<WinStrategy> =
        listOf(
            HorizontalWinStrategy(),
            VerticalWinStrategy(),
            DiagonalWinStrategy(),
        ),
) {
    fun check(
        board: Board,
        position: Position,
    ): Boolean {
        return strategies.any { it(board, position) }
    }

    companion object {
        const val CONSECUTIVE_COINS = 4
    }
}
