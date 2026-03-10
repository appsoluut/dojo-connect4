package com.appsoluut.connect4

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GameHistory(
    val file: String,
) {
    fun record(input: String) {
        val log = File(file)
        log.parentFile?.mkdirs()

        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val timestamp = currentTime.format(formatter)

        log.appendText("[$timestamp] $input\n")
    }
}
