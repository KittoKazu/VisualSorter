package nl.kittokazu

import javafx.application.Platform
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class DotVisualizer : Visualizer {
    private lateinit var circles: MutableList<Circle>

    private val bubbleSpace = 20.0 //Space between bubbles
    private val bubbleCorrection = 15.0 // Offset from the left of the Pane
    private val bubbleRadius = 10.0 //Radius of circles

    /*
    Initializes the circles and adds them to a container
     */
    override fun init(values: IntArray, chartHeight: Double, container: Pane){
        val maxVal = values.maxOrNull() ?: 1 //Get max value to scale dot height

        circles = values.mapIndexed { index, value ->
            val x = index * bubbleSpace + bubbleCorrection  // Maps X values for bubbles
            val y = chartHeight - (value.toDouble() / maxVal * chartHeight) //Maps Y values for bubbles

            Circle(bubbleRadius).apply {
                centerX = x
                centerY = y
                fill = Color.CORNFLOWERBLUE //Creates and colors circles
            }
        }.toMutableList()

        container.children.addAll(circles) //Add all circles to the containers
    }

    /*
    Tracks the current algorithm and edits the circles accordingly
     */
    override fun track(i: Int, j: Int) {
        Platform.runLater {
            val tempY = circles[i].centerY
            circles[i].centerY = circles[j].centerY
            circles[j].centerY = tempY // Swaps Y position of the circles (X positions don't need to be swapped)

            circles[i].fill = Color.ORANGE
            circles[j].fill = Color.ORANGE //Highlights dots to show where the algorithm is and what's swapped.
        }

        Thread.sleep(speedTime) //Pauses the process for a second so animation isn't too fast

        Platform.runLater {
            circles[i].fill = Color.CORNFLOWERBLUE
            circles[j].fill = Color.CORNFLOWERBLUE //Reset color of the circles
        }
    }
}

