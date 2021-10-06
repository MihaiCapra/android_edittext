package com.mihai.textfield.ui

import com.mihai.textfield.TestCase
import com.mihai.textfield.core.countWords
import com.mihai.textfield.core.splitIgnoreEmpty
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class ExtensionsTest {

    @TestFactory
    fun `test countWords method`() = listOf(
        TestCase("empty string", "") to "0",
        TestCase("one word", "word") to "1",
        TestCase("two words", "one two") to "2",
        TestCase("three words", "one two three") to "3",
        TestCase("multiple white spaces", "another test    with multiple white   spaces") to "6",
        TestCase("random characters and white spaces", "test   test12312 .arts.    ") to "3"
    ).map { (input, expected) ->
        DynamicTest.dynamicTest(input.testName) {
            Assertions.assertEquals(expected, input.data.countWords())
        }
    }

    @TestFactory
    fun `test splitIgnoreEmpty method`() = listOf(
        TestCase("empty string", "") to emptyList(),
        TestCase("one word", "apple") to listOf("apple"),
        TestCase("two words", "one two") to listOf("one", "two"),
        TestCase("three words", "one two three") to listOf("one", "two", "three"),
        TestCase("multiple white spaces", "another test    with multiple white   spaces") to listOf(
            "another",
            "test",
            "with",
            "multiple",
            "white",
            "spaces"
        ),
        TestCase(
            "random characters and white spaces",
            "test   test12312 .arts.    "
        ) to listOf("test", "test12312", ".arts.")
    ).map { (input, expected) ->
        DynamicTest.dynamicTest(input.testName) {
            val actual = input.data.splitIgnoreEmpty("\\s+".toRegex())
            Assertions.assertEquals(expected.size, actual.size)
            Assertions.assertTrue(expected == actual)
        }
    }
}