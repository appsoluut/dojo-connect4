package com.appsoluut.connect4

interface Output {
    fun clear()

    fun print(output: String)

    fun println(output: String)
}

class TerminalOutput : Output {
    override fun clear() {
        // ANSI code for clear screen
        print("\u001b[H\u001b[2J")
    }

    override fun print(output: String) {
        kotlin.io.print(output)
    }

    override fun println(output: String) {
        kotlin.io.println(output)
    }
}
