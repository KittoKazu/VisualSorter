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
/*
Checks whether the string only contains usable characters. Returns True or False
 */
fun CheckIfUsable(input: String): Boolean {
    val Filter = Regex("^[\\d,\\s]*$") //regex expression filters out d(digits), commas and s(whitespaces)
    return input.matches(Filter)
}

/*
Converts a string into a list, ignoring spaces.
 */
fun ConvertToList(input:String): List<Int> {
    return input
        .split(",") //Filters values seperated by comma
        .map {it.trim()}.filter{it.isNotEmpty()} //Filters out spaces before and after values
        .map {it.toInt()} //Turns the values to Int
}