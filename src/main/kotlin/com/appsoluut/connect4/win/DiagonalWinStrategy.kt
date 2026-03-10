package com.appsoluut.connect4.win

import com.appsoluut.connect4.Board
import com.appsoluut.connect4.Position

class DiagonalWinStrategy : WinStrategy {
    override fun invoke(
        board: Board,
        position: Position,
    ): Boolean {
        if (countConsecutive(board = board, position = position, rowDelta = 1, colDelta = 1) == WinCondition.CONSECUTIVE_COINS) {
            return true
        }

        if (countConsecutive(board = board, position = position, rowDelta = -1, colDelta = 1) == WinCondition.CONSECUTIVE_COINS) {
            return true
        }

        return false
    }

    private fun countConsecutive(
        board: Board,
        position: Position,
        rowDelta: Int,
        colDelta: Int,
    ): Int {
        var consecutive = 1
        consecutive += countDirection(board, position, rowDelta, colDelta)
        consecutive += countDirection(board, position, -rowDelta, -colDelta)
        return consecutive
    }

    private fun countDirection(
        board: Board,
        position: Position,
        rowDelta: Int,
        colDelta: Int,
    ): Int {
        var count = 0
        var row = position.row + rowDelta
        var col = position.column + colDelta

        while (row in 1..board.rows && col in 1..board.columns) {
            if (board.positionAt(row, col).coin == position.coin) {
                count++
                row += rowDelta
                col += colDelta
            } else {
                break
            }
        }

        return count
    }
}
