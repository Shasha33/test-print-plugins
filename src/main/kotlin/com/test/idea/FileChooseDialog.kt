package com.test.idea

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JPanel


class FileChooseDialog(private val project: Project?) : DialogWrapper(true) {
    companion object {
        const val MIN_DIALOG_WIDTH = 350
        const val MIN_FILE_CHOOSE_HEIGHT = 30
    }

    private val fileChooseField = TextFieldWithBrowseButton()
    private val dialogPanel = JPanel(BorderLayout())

    init {
        init()
        setOKButtonText("Run")
    }

    override fun createCenterPanel(): JComponent? {
        title = "Choose a file to print"

        dialogPanel.minimumSize = Dimension(
            MIN_DIALOG_WIDTH,
            MIN_FILE_CHOOSE_HEIGHT
        )
        fileChooseField.addBrowseFolderListener(
            null, null,
            project, FileChooserDescriptorFactory.createSingleFileDescriptor()
        )
        dialogPanel.add(fileChooseField)
        return dialogPanel
    }
}