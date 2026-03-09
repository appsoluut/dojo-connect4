package com.appsoluut.connect4

class FakeOutput : Output {
    var buffer: String = ""

    override fun clear() {
        buffer = ""
    }

    override fun print(output: String) {
        buffer += output
    }

    override fun println(output: String) {
        buffer += "$output\n"
    }
}
