package com.appsoluut.connect4

interface Renderer {
    fun renderCoin(coin: Coin): String

    fun renderPlayerTurn(player: Player): String

    fun renderBoard(board: Board): String

    fun renderInstructions(board: Board): String
}
