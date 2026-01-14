package com.appsoluut.connect4

class Connect4 {
    private val board = Board()

    fun getBoard(): Board = board

    fun isEmptyBoard() = board.isEmpty()
}
