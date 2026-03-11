package com.appsoluut.connect4.history

import java.io.File

class FileLogWriter(
    private val file: File,
) : LogWriter {
    override fun writeln(line: String) {
        file.parentFile?.mkdirs()
        file.appendText("$line\n")
    }
}
