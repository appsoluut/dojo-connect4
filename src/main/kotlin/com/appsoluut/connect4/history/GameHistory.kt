package com.appsoluut.connect4.history

import java.time.Clock
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GameHistory(
    private val logWriter: LogWriter,
    private val clock: Clock = Clock.systemDefaultZone(),
) {
    fun record(input: String) {
        val currentTime = LocalDateTime.now(clock)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val timestamp = currentTime.format(formatter)

        logWriter.writeln("[$timestamp] $input")
    }
}
