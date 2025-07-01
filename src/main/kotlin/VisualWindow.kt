package nl.kittokazu

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import javafx.geometry.Insets

class MainApp : Application() {

    companion object {
        lateinit var instance: MainApp
            private set
    }

    init {
        instance = this
    }

    val inputField = TextField()
    val toggleQuickSort = CheckBox("Quick sort")
    val toggleBubbleSort = CheckBox("Bubble sort")
    val buttonPressed = Label("")

    override fun start(stage: Stage) {

        val titleLabel = Label("Welcome to the Visual Sorter")

        inputField.promptText = "Give values, separate with , ."  // hint text inside input
        inputField.maxWidth = 300.0  // optional: limit width so it looks nice

        val startButton = Button("Start")
        startButton.setOnAction {
            val inputText = inputField.text.ifBlank { "(empty)" }

            val selectedOptions = mutableListOf<String>()
            if (toggleQuickSort.isSelected) selectedOptions.add(toggleQuickSort.text)
            if (toggleBubbleSort.isSelected) selectedOptions.add(toggleBubbleSort.text)

            val options = if (selectedOptions.isEmpty()) "None" else selectedOptions.joinToString(", ")

            buttonPressed.text = "Input: $inputText   Selected: $options"
        }

        val root = VBox(10.0,
            titleLabel,
            toggleQuickSort,
            toggleBubbleSort,
            inputField,
            startButton,
            buttonPressed
        )
        root.padding = Insets(20.0)

        stage.title = "VisualSorter ver. 1.0.0"
        stage.scene = Scene(root,800.0, 600.0)
        stage.show()
    }
}
