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
                if (consecutive == 4) {
                    return true
                }
            } else {
                consecutive = 0
            }
        }
        return false
    }
}
