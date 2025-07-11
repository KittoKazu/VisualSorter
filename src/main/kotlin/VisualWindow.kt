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

class MainApp : Application() {

    val inputField = TextField() // Text field for entering values
    val textFieldWidth = 300.0 //Max width of text field, for easy changing

    val inputFeedback = Label("") //Label used to show user their own input, or to show wrong input
    val algRadioLabel = Label("Choose your algorithm")
    val visRadioLabel = Label("Choose your visualization")
    val clearLine = Label(" ") //Empty label to use as an empty line

    val chartHeight = 200.0 //Height of visual containers, for easy changing
    val container = javafx.scene.layout.Pane() //Container for visualisations

    val algToggleGroup = ToggleGroup() // Toggle groups for Radio buttons
    val visToggleGroup = ToggleGroup()

    val toggleQuickSort = MakeRadioButton("Quick sort", algToggleGroup) // Creating Radio buttons with a class
    val toggleBubbleSort = MakeRadioButton("Bubble sort",algToggleGroup)
    val toggleGraphVisualizer = MakeRadioButton("Graph visualizer", visToggleGroup)
    val toggleDotVisualizer = MakeRadioButton("Dot visualizer", visToggleGroup)

    val visualGraph = GraphVisualizer()//Creates an object to visualise with a graph
    val visualDots = DotVisualizer() //Creates an object to visualise with dots

    override fun start(stage: Stage) {

        val titleLabel = Label("Welcome to the Visual Sorter")

        inputField.promptText = "Give values, separate with , ."  // Text inside input field
        inputField.maxWidth = textFieldWidth  // Limits text field width

        var graphChosen = false //Booleans for choosing visualisation type, makes sure updateVisuals cannot change target while algorithm is running
        var dotChosen = false

        val startButton = Button("Start")
        startButton.setOnAction { //activates when startButton is pressed
            startButton.isDisable = true //Disables startButton so user cannot start multiple sorting instances simultaneously
            container.children.clear() //Clears the container in order to prevent visual bugs

            graphChosen = false //Resets visualisation choices to prevent bugs
            dotChosen = false

            val inputText = inputField.text.ifBlank { "ERROR" }

            if (checkIfUsable(inputText)) { //Checks if input can be used
                inputFeedback.text = "Input: $inputText" //Prints user input

                val inputArray = convertToList(inputText) //Turns input into list<Int>

                if (toggleGraphVisualizer.radioButton.isSelected) {
                    visualGraph.initGraph(inputArray.toIntArray(), chartHeight, container) //Initializes the visualisation
                    graphChosen = true
                }
                if (toggleDotVisualizer.radioButton.isSelected) {
                    visualDots.initDots(inputArray.toIntArray(), chartHeight, container)
                    dotChosen = true
                }

                if (toggleBubbleSort.radioButton.isSelected) {
                    Thread {
                        BubbleSort().sort(inputArray.toIntArray()) { values, i, j -> updateVisuals(i,j,graphChosen,dotChosen) } // Start sorting and update visualisation
                        Platform.runLater { startButton.isDisable = false } //Enables button after algorithm is done.
                    }.start()
                }
                if (toggleQuickSort.radioButton.isSelected) {
                    Thread {
                        QuickSort().sort(inputArray.toIntArray()) { values, i, j -> updateVisuals(i,j,graphChosen,dotChosen) }
                        Platform.runLater { startButton.isDisable = false }
                    }.start()
                }
            }
            else{
                inputFeedback.text = "Please make sure input only contains numbers and comma's (,)"
                startButton.isDisable = false //Enables button so user can submit new input
            }
        }

        val sorterBox = VBox(10.0,
            titleLabel,
            clearLine,
            algRadioLabel,
            toggleQuickSort.radioButton,
            toggleBubbleSort.radioButton,
            visRadioLabel,
            toggleGraphVisualizer.radioButton,
            toggleDotVisualizer.radioButton,
            inputField,
            startButton,
            inputFeedback,
            container
        ) // Puts all units inside a visual box to display
        sorterBox.padding = Insets(20.0) //Creates an empty border around the visual box

        stage.title = "VisualSorter ver. 1.0.0" //Sets the title at the top of the window
        stage.scene = Scene(sorterBox,650.0, 800.0) //Sets the default size of the window
        stage.show()
    }
    /*
    Chooses correct algorithm to track and updates visualisation
     */
    fun updateVisuals(i: Int, j: Int, graphChosen:Boolean, dotChosen:Boolean) {
        if (graphChosen) {
            visualGraph.trackAlgorithm(i, j)
        }
        if (dotChosen) {
            visualDots.trackAlgorithm(i, j)
        }
    }
}

/*
Class to create a radio button with custom label and ToggleGroup
 */
class MakeRadioButton(label: String, group: ToggleGroup){
    val radioButton= RadioButton(label).apply{
        toggleGroup = group
    }
}