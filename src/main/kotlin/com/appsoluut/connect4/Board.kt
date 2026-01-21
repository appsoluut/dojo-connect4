package com.appsoluut.connect4

class Board {
    companion object {
        const val BOARD_ROWS = 6
        const val BOARD_COLUMNS = 7
    }

    private var board: Set<Field> = emptySet()

    constructor() {
        reset()
    }

    fun reset() {
        val newBoard = mutableSetOf<Field>()
        repeat(BOARD_ROWS * BOARD_COLUMNS) { index ->
            newBoard.add(Field.EMPTY)
        }
        board = newBoard.toSet()
    }

    fun isEmpty(): Boolean = board.all { it == Field.EMPTY }
}
