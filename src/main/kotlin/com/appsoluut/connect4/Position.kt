package com.appsoluut.connect4

data class Position(
    val column: Int,
    val row: Int,
    val isEmpty: Boolean = true,
)
