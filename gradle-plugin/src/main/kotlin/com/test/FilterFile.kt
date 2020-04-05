package com.test

import java.util.regex.Pattern

fun filterFile(lines: List<String>): String {
    var cnt = 0
    val result = mutableListOf<String>()
    val skipLinePattern = Pattern.compile("#skip ([1-9][0-9]*).*")
    for (line in lines) {
        val skipLineMatcher = skipLinePattern.matcher(line)
        if (skipLineMatcher.matches()) {
            cnt = skipLineMatcher.group(1).toInt() + 1
        } else if (cnt <= 0) {
            result.add(line)
        }
        cnt--
    }
    return result.joinToString("\n")
}