package com.test.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import com.test.*
import java.io.File

@Suppress("UnstableApiUsage")
open class PrintTask : DefaultTask() {
    private var filename: String? = null

    @Option(option = "filename", description = "Name of a file to print")
    fun setUrl(file: String) {
        filename = file
    }

    @TaskAction
    fun print() {
        val file = File(filename!!)
        if (!file.exists()) {
            print("No such file found")
            return
        }
        try {
            val lines = file.readLines()
            print(filterFile(lines))
        } catch (e: Exception) {
            println("Exception occurred : $e")
        }
    }
}