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

    fun isEmpty(): Boolean = positions.all { it.coin == Coin.None }

    fun placeCoinAt(position: Position): Board {
        val mutablePositions = positions.toMutableList()
        mutablePositions.set(
            index = position.row * columns + position.column,
            element = position.copy(coin = position.coin),
        )
        return Board(rows, columns, mutablePositions.toList())
    }

    fun isPositionAvailableAt(
        row: Int,
        column: Int,
    ): Boolean = positions[row * columns + column].coin == Coin.None

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
                            coin = Coin.None,
                        )
                    },
            )
    }
}
