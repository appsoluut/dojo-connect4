package com.appsoluut.connect4

interface Renderer {
    fun renderCoin(coin: Coin?): String

    fun renderBoard(board: Board): String
}
