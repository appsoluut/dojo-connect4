package com.appsoluut.connect4

class Board private constructor(
    val rows: Int,
    val columns: Int,
    private val positions: List<Position>,
) {
    fun positionAt(
        row: Int,
        column: Int,
    ): Position = positions[row * columns + column]

    fun isEmpty(): Boolean = positions.all { it.isEmpty }

    companion object {
        fun empty(
            rows: Int = 6,
            columns: Int = 7,
        ): Board =
            Board(
                rows = rows,
                columns = columns,
                positions =
                List(rows * columns) {
                    Position(
                        column = it % columns,
                        row = it / columns,
                    )
                },
            )
    }
}
