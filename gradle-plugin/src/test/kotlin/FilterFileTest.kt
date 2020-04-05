package com.test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FilterFileTest {
    private fun assertCorrectFilter(initText: String, expectedResult: String) {
        val result = filterFile(initText.lines())
        assertEquals(expectedResult, result)
    }

    @Test
    fun fileWithoutSkipTest() {
        val text = """
            1a 2b
            2a
            3a 2a
            12
        """.trimIndent()
        assertCorrectFilter(text, text)
    }

    @Test
    fun simpleSkipTest() {
        val text = """
            1a 2b
            2a
            #skip 1
            3a 2a
            12
        """.trimIndent()

        val resultText = """
            1a 2b
            2a
            12
        """.trimIndent()
        assertCorrectFilter(text, resultText)
    }

    @Test
    fun skipNegativeNumberTest() {
        val text = """
            1a 2b
            2a
            #skip -1
            3a 2a
            12
        """.trimIndent()
        assertCorrectFilter(text, text)
    }

    @Test
    fun skipWithoutSharpTest() {
        val text = """
            1a 2b
            2a
            skip 1
            3a 2a
            12
        """.trimIndent()
        assertCorrectFilter(text, text)
    }

    @Test
    fun skipInTheMiddleOfALineTest() {
        val text = """
            1a 2b
            2a
            3a #skip 1 2a
            12
        """.trimIndent()
        assertCorrectFilter(text, text)
    }

    @Test
    fun simpleZeroTest() {
        val text = """
            1a 2b
            2a
            #skip 0
            3a 2a
            12
        """.trimIndent()
        assertCorrectFilter(text, text)
    }

    @Test
    fun severalSkipsTest() {
        val text = """
            #skip 1
            1a 2b
            2a
            #skip 2
            3a 2a
            12
            a
            b
            #skip 1
            abc 23
        """.trimIndent()

        val resultText = """
            2a
            a
            b
        """.trimIndent()
        assertCorrectFilter(text, resultText)
    }

    @Test
    fun severalSkipsWithIncorrectTest() {
        val text = """
            #skip 1
            1a 2b
            2a
            #skip -2
            3a 2a
            12
            a
            b
            #skip 1
            abc 23
        """.trimIndent()

        val resultText = """
            2a
            #skip -2
            3a 2a
            12
            a
            b
        """.trimIndent()
        assertCorrectFilter(text, resultText)
    }

    @Test
    fun severalSkipsWithCrossTest() {
        val text = """
            t
            #skip 3
            1a 2b
            2a
            #skip 2
            3a 2a
            12
            a
            b
            abc 23
        """.trimIndent()

        val resultText = """
            t
            a
            b
            abc 23
        """.trimIndent()
        assertCorrectFilter(text, resultText)
    }

    @Test
    fun nonEmptySkipLineTest() {
        val text = """
            1a 2b
            2a
            #skip 1 asdag
            3a 2a
            12
        """.trimIndent()

        val resultText = """
            1a 2b
            2a
            12
        """.trimIndent()
        assertCorrectFilter(text, resultText)
    }

    @Test
    fun outOfBoundSkipTest() {
        val text = """
            1a 2b
            2a
            #skip 10
            3a 2a
            12
            5b 
            6b
        """.trimIndent()

        val resultText = """
            1a 2b
            2a
        """.trimIndent()
        assertCorrectFilter(text, resultText)
    }
}