package com.appsoluut.connect4

class Board {
    val rows = 6

    val columns = 7
    val positions = List(rows * columns) { Position(column = it % columns, row = it / columns) }

    fun positionAt(
        row: Int,
        column: Int,
    ): Position {
        return Position(
            row = row,
            column = column,
        )
    }
}
