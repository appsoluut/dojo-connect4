package com.appsoluut.connect4

enum class Player {
    First,
    Second,
}

fun Player.coin(): Coin =
    when (this) {
        Player.First -> Coin.Yellow
        Player.Second -> Coin.Red
    }
