package com.appsoluut.connect4.win

import com.appsoluut.connect4.Board
import com.appsoluut.connect4.Position

class DrawWinStrategy : WinStrategy {
    override fun invoke(
        board: Board,
        position: Position,
    ): GameResult = if (board.isFull()) GameResult.Draw else GameResult.Running
}
