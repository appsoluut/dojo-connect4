package com.appsoluut.connect4

class Board {
    val rows = 6
    val columns = 7

    val positions = List(rows * columns) { Position() }
}
