package com.test.idea

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class PrintAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val dialog = FileChooseDialog(e.project)
        dialog.show()
    }
}