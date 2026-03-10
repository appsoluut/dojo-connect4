package com.appsoluut.connect4

class Board private constructor(
    val rows: Int,
    val columns: Int,
    private val positions: List<Position>,
) {
    fun positionAt(
        row: Int,
        column: Int,
    ): Position = positions[(row - 1) * columns + (column - 1)]

    fun isEmpty(): Boolean = positions.all { it.coin == Coin.None }

    fun isFull(): Boolean = !positions.any { it.coin == Coin.None }

    fun placeCoinAt(position: Position): Board {
        val mutablePositions = positions.toMutableList()
        mutablePositions.set(
            index = (position.row - 1) * columns + (position.column - 1),
            element = position.copy(coin = position.coin),
        )
        return Board(rows, columns, mutablePositions.toList())
    }

    fun isPositionAvailableAt(
        row: Int,
        column: Int,
    ): Boolean = positions[(row - 1) * columns + (column - 1)].coin == Coin.None

    fun dropCoinIn(
        coin: Coin,
        column: Int,
    ): Result<MoveResult> {
        for (row in 1..rows) {
            if (isPositionAvailableAt(row, column)) {
                val position = Position(row = row, column = column, coin = coin)
                return Result.success(MoveResult(board = placeCoinAt(position), position = position))
            }
        }
        return Result.failure(IllegalStateException("Column $column is full."))
    }

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
                            column = it % columns + 1,
                            row = it / columns + 1,
                            coin = Coin.None,
                        )
                    },
            )
    }
}

data class MoveResult(
    val board: Board,
    val position: Position,
)
