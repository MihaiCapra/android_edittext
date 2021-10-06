package com.mihai.textfield.core

/**
 * countWords - counts number of words by splitting the text using regex
 *
 * @return number of words as string
 */
fun String.countWords(): String =
    trim().splitIgnoreEmpty("\\s+".toRegex()).size.toString()

/**
 * splitIgnoreEmpty - splits this char sequence to a list of strings that matches
 * the regular expression and ignores empty strings
 *
 * @param regex - a regular expression
 *
 * @return a list of strings
 */
fun CharSequence.splitIgnoreEmpty(regex: Regex): List<String> =
    split(regex).filter { it.isNotEmpty() }