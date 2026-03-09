package com.appsoluut.connect4

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

@DisplayName("Terminal Output should")
class TerminalOutputTest {
    @Test
    @DisplayName("print Hello World on one line with a line-break at the end")
    fun testPrintAndPrintln() {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)
        val originalOut = System.out
        System.setOut(printStream)

        val output = TerminalOutput()
        output.print("Hello")
        output.println(" World")

        System.out.flush()
        System.setOut(originalOut)

        val result = outputStream.toString()

        assertEquals("Hello World\n", result)
    }

    @Test
    @DisplayName("clear the screen with a ANSI code")
    fun testClear() {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)
        val originalOut = System.out
        System.setOut(printStream)

        val output = TerminalOutput()
        output.clear()

        System.out.flush()
        System.setOut(originalOut)

        val result = outputStream.toString()

        // ANSI escape code for clearing screen
        assertTrue(result.contains("\u001b[H\u001b[2J"))
    }
}
