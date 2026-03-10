package com.appsoluut.connect4

class Turn {
    private var currentPlayer = Player.First

    fun getCurrentPlayer(): Player = currentPlayer

    fun next() {
        val players = Player.entries
        currentPlayer = players[(players.indexOf(currentPlayer) + 1) % players.size]
    }
}
