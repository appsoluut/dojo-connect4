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
        const val ROWS = 6
        const val COLUMNS = 7

        fun empty(
            rows: Int = ROWS,
            columns: Int = COLUMNS,
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
