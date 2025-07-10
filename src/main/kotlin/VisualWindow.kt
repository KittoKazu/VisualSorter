package nl.kittokazu

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import javafx.scene.control.TextField
import javafx.geometry.Insets
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class MainApp : Application() {

    val inputField = TextField()
    val algGroup = ToggleGroup()
    val buttonPressed = Label("")
    val algContainer = javafx.scene.layout.HBox(10.0)
    val chartHeight = 300.0

    val toggleQuickSort = RadioButton("Quick sort").apply {
        toggleGroup = algGroup
    }

    val toggleBubbleSort = RadioButton("Bubble sort").apply {
        toggleGroup = algGroup
    }

    override fun start(stage: Stage) {

        val titleLabel = Label("Welcome to the Visual Sorter")

        inputField.promptText = "Give values, separate with , ."  // Text inside input field
        inputField.maxWidth = 300.0  // limit width so it looks nice

        val startButton = Button("Start")
        startButton.setOnAction {
            algContainer.children.clear() //Clears the visual graph before doing anything else.

            val inputText = inputField.text.ifBlank { "" }

            if (CheckIfUsable(inputText)) {
                val inputArray = ConvertToList(inputText)

                val maxVal = inputArray.maxOrNull() ?: 1 // Get the highest value, to scale the bars
                val bars = inputArray.map { value ->
                    val height = (value.toDouble() / maxVal * chartHeight).coerceAtLeast(1.0) // Scales the entire chart
                    Rectangle(10.0, height).apply {
                        fill = Color.DARKSEAGREEN //create and color the bars
                    }
                }.toMutableList()

                algContainer.children.addAll(bars)

                if (toggleBubbleSort.isSelected) {
                    Thread {
                        BubbleSort().sort(inputArray.toIntArray()) { values, i, j ->
                            Platform.runLater {
                                val tempHeight = bars[i].height
                                bars[i].height = bars[j].height
                                bars[j].height = tempHeight

                                bars[i].fill = Color.ORANGE
                                bars[j].fill = Color.ORANGE
                            }
                            Thread.sleep(speedTime)
                            Platform.runLater {
                                bars[i].fill = Color.DARKSEAGREEN
                                bars[j].fill = Color.DARKSEAGREEN
                            }
                        }
                    }.start()
                }
                if (toggleQuickSort.isSelected) {
                    Thread {
                        QuickSort().sort(inputArray.toIntArray()) { values, i, j ->
                            Platform.runLater {
                                val tempHeight = bars[i].height
                                bars[i].height = bars[j].height
                                bars[j].height = tempHeight

                                bars[i].fill = Color.ORANGE
                                bars[j].fill = Color.ORANGE
                            }
                            Thread.sleep(speedTime)
                            Platform.runLater {
                                bars[i].fill = Color.DARKSEAGREEN
                                bars[j].fill = Color.DARKSEAGREEN
                            }
                        }
                    }.start()
                }
            }
            buttonPressed.text = "Input: $inputText"
        }

        val root = VBox(10.0,
            titleLabel,
            toggleQuickSort,
            toggleBubbleSort,
            inputField,
            startButton,
            buttonPressed,
            algContainer,
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