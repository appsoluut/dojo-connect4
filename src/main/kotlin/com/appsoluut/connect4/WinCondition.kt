package com.appsoluut.connect4

class WinCondition {
    fun checkHorizontalWin(
        board: Board,
        position: Position,
    ): Boolean {
        var consecutive = 0
        for (column in 1..board.columns) {
            val coin = board.positionAt(row = position.row, column = column).coin
            if (coin == position.coin) {
                consecutive++
                if (consecutive == CONSECUTIVE_COINS) {
                    return true
                }
            } else {
                consecutive = 0
            }
        }
        return false
    }

    fun checkVerticalWin(
        board: Board,
        position: Position,
    ): Boolean {
        var consecutive = 0
        for (row in 1..board.rows) {
            val coin = board.positionAt(row = row, column = position.column).coin
            if (coin == position.coin) {
                consecutive++
                if (consecutive == CONSECUTIVE_COINS) {
                    return true
                }
            } else {
                consecutive = 0
            }
        }
        return false
    }

    companion object {
        const val CONSECUTIVE_COINS = 4
    }
}
