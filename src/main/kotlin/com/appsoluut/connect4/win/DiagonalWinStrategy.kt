package com.appsoluut.connect4.win

import com.appsoluut.connect4.Board
import com.appsoluut.connect4.Position

class DiagonalWinStrategy : WinStrategy {
    override fun invoke(
        board: Board,
        position: Position,
    ): GameResult {
        val directions = listOf(1, -1)
        val result =
            if (directions.any { rowDelta ->
                    countConsecutive(
                        board = board,
                        position = position,
                        rowDelta = rowDelta,
                    ) == WinCondition.CONSECUTIVE_COINS
                }
            ) {
                GameResult.Win
            } else {
                GameResult.Running
            }
        return result
    }

    private fun countConsecutive(
        board: Board,
        position: Position,
        rowDelta: Int,
    ): Int {
        var consecutive = 1
        consecutive += countDirection(board, position, rowDelta, 1)
        consecutive += countDirection(board, position, -rowDelta, -1)
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
