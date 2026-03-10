package com.appsoluut.connect4

interface Input {
    fun readln(): String?

    fun readColumn(): Int?
}

class TerminalInput(private val inputProvider: () -> String? = ::readln) : Input {
    override fun readln(): String? = inputProvider()

    override fun readColumn(): Int? = inputProvider()?.toIntOrNull()
}
