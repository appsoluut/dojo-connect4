package com.appsoluut.connect4.win

import com.appsoluut.connect4.Board
import com.appsoluut.connect4.Position

class VerticalWinStrategy : WinStrategy {
    override fun invoke(
        board: Board,
        position: Position,
    ): GameResult {
        var consecutive = 0
        for (row in 1..board.rows) {
            val coin = board.positionAt(row = row, column = position.column).coin
            if (coin == position.coin) {
                consecutive++
                if (consecutive == WinCondition.CONSECUTIVE_COINS) {
                    return GameResult.Win
                }
            } else {
                consecutive = 0
            }
        }
        return GameResult.Running
    }
}
