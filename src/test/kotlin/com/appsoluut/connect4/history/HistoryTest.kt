package com.appsoluut.connect4.history

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import kotlin.test.Test

@DisplayName("Game History should")
class HistoryTest {
    @Test
    @DisplayName("record the last win condition")
    fun log() {
        val writer = InMemoryLogWriter()
        val fixedClock = Clock.fixed(Instant.parse("1981-12-04T12:00:00Z"), ZoneId.of("UTC"))
        val history =
            GameHistory(
                logWriter = writer,
                clock = fixedClock,
            )

        history.record("I was born!")

        assertTrue(writer.lines.first().contains("[1981-12-04 12:00:00] I was born!"))
    }
}

class InMemoryLogWriter : LogWriter {
    val lines = mutableListOf<String>()

    override fun writeln(line: String) {
        lines.add(line)
    }
}
