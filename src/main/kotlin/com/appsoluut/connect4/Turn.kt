package com.appsoluut.connect4

class Turn {
    private var currentPlayer = Player.First

    fun getCurrentPlayer(): Player = currentPlayer

    fun advance() {
        val players = Player.entries
        currentPlayer = players[(currentPlayer.ordinal + 1) % players.size]
    }
}
