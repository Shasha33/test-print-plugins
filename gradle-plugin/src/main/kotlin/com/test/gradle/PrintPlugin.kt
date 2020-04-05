package com.test.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class PrintPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.create("print", PrintTask::class.java)
    }
}