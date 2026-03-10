package com.appsoluut.connect4.win

import com.appsoluut.connect4.Board
import com.appsoluut.connect4.Position

interface WinStrategy {
    operator fun invoke(
        board: Board,
        position: Position,
    ): Boolean
}
