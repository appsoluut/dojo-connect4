package com.appsoluut.connect4

class Board {
    val rows = 6
    val columns = 7

    private var board: Set<Field> = emptySet()

    constructor() {
        initialize()
    }

    fun initialize() {
        val newBoard = mutableSetOf<Field>()
        repeat(rows * columns) { index ->
            newBoard.add(Field.EMPTY)
        }
        board = newBoard.toSet()
    }

    fun isEmpty(): Boolean = board.all { it == Field.EMPTY }
}
