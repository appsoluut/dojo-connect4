package com.appsoluut.connect4

interface Input {
    fun readColumn(): Int?
}

class TerminalInput : Input {
    override fun readColumn(): Int? = readln().toIntOrNull()
}
