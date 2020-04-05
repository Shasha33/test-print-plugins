package com.test.idea

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.test.filterFile
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextPane


class FileChooseDialog(private val project: Project?) : DialogWrapper(true) {
    companion object {
        const val MIN_DIALOG_WIDTH = 350
        const val MIN_FILE_CHOOSE_HEIGHT = 30
        const val MIN_FILE_SHOW_HEIGHT = 150
    }

    private val fileChooseField = TextFieldWithBrowseButton()
    private val dialogPanel = JPanel(BorderLayout())
    private var isFileShowed = false

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

    private fun getChosenFileName(): String {
        return fileChooseField.text
    }

    override fun doOKAction() {
        if (isFileShowed) {
            super.doOKAction()
            return
        }
        isFileShowed = true
        val filename = getChosenFileName()
        val textToPrint = filterFile(filename)
        dialogPanel.remove(fileChooseField)
        dialogPanel.add(JScrollPane(JTextPane().apply {
            text = textToPrint
        }))
        dialogPanel.minimumSize = Dimension(
            MIN_DIALOG_WIDTH,
            MIN_FILE_SHOW_HEIGHT
        )
        setOKButtonText("OK")
        dialogPanel.updateUI()
    }
}