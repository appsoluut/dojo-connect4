package com.appsoluut.connect4

class Connect4 private constructor(
    val board: Board,
) {
    companion object {
        fun newGame(): Connect4 = Connect4(Board.empty())
    }
}
