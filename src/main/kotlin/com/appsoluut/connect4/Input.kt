package com.appsoluut.connect4

interface Input {
    fun readColumn(): Int?
}

class TerminalInput(private val inputProvider: () -> String? = ::readln) : Input {
    override fun readColumn(): Int? = inputProvider()?.toIntOrNull()
}
