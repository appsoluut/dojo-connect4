package com.appsoluut.connect4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

@DisplayName("Validator should")
class ValidatorTest {
    @Test
    @DisplayName("check if column number is in valid range")
    fun checkColumnInRange() {
        val validator = Validator(Board.empty())

        val input = 4

        assertTrue(validator.isColumnValid(input))
    }
}
