package nl.kittokazu

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import javafx.scene.control.TextField
import javafx.geometry.Insets

class MainApp : Application() {

    val inputField = TextField()
    val algGroup = ToggleGroup()
    val buttonPressed = Label("")
    val algContainer = javafx.scene.layout.Pane()
    val dotContainer = javafx.scene.layout.Pane()
    val chartHeight = 200.0

    val toggleQuickSort = RadioButton("Quick sort").apply {
        toggleGroup = algGroup
    }
    val toggleBubbleSort = RadioButton("Bubble sort").apply {
        toggleGroup = algGroup
    }

    override fun start(stage: Stage) {

        val titleLabel = Label("Welcome to the Visual Sorter")

        inputField.promptText = "Give values, separate with , ."  // Text inside input field
        inputField.maxWidth = 300.0  // Limits text field width

        val startButton = Button("Start")
        startButton.setOnAction { //activates when startButton is pressed
            algContainer.children.clear() //Clears the graph container before doing anything else in order to prevent bugs.
            dotContainer.children.clear()

            val inputText = inputField.text.ifBlank { "" }

            if (CheckIfUsable(inputText)) {
                val inputArray = ConvertToList(inputText)

                val visualGraph = GraphVisualizer() //Creates a graph instance to show the algorithm
                visualGraph.initGraph(inputArray.toIntArray(),chartHeight,algContainer) //initializes the graph

                val visualDots = DotVisualizer() //Creates a class for showing the dots
                visualDots.initDots(inputArray.toIntArray(),chartHeight,dotContainer)

                if (toggleBubbleSort.isSelected) {
                    Thread {
                        BubbleSort().sort(inputArray.toIntArray()) { values, i, j ->
                            visualGraph.trackAlgorithm(i, j) //Tracks the changes in the graph and changes color when checking
                            visualDots.trackAlgorithm(i, j)
                        }
                    }.start()
                }
                if (toggleQuickSort.isSelected) {
                    Thread {
                        QuickSort().sort(inputArray.toIntArray()) { values, i, j ->
                            visualGraph.trackAlgorithm(i, j)
                            visualDots.trackAlgorithm(i, j)
                        }
                    }.start()
                }
            }
            buttonPressed.text = "Input: $inputText"
        }

        val sorterBox = VBox(10.0,

            titleLabel,
            toggleQuickSort,

            toggleBubbleSort,
            inputField,
            startButton,
            buttonPressed,
            algContainer,
            dotContainer
        ) // Puts all units inside a visual box to display
        sorterBox.padding = Insets(20.0) //Creates an empty border around the visual box

        stage.title = "VisualSorter ver. 1.0.0" //Sets the title at the top of the window
        stage.scene = Scene(sorterBox,650.0, 800.0) //Sets the default size of the window
        stage.show()
    }
}
/*
Checks whether the string only contains usable characters. Returns True or False
 */
fun CheckIfUsable(input: String): Boolean {
    val filter = Regex("^[\\d,\\s]*$") //regex expression filters out d(digits), commas and s(whitespaces)
    return input.matches(filter)
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

