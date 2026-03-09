package com.appsoluut.connect4

class Validator(
    val board: Board,
) {
    fun isColumnValid(column: Int): Boolean = column in (1..board.columns)
}
