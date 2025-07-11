package nl.kittokazu

import javafx.application.Platform
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class GraphVisualizer : Visualizer {
    private lateinit var bars: MutableList<Rectangle>
    private var chartHeight = 0.0 //chartHeight declared locally so it can be updated after initializing
    private val barWidth = 12.0 // Width of bars in graph, declared here for easy editing
    private val barDistance = barWidth + 3.0 //Distance between bars, cannot be smaller than barWidth
    /*
    Initializes and draws the initial graph
     */
    override fun init(values: IntArray, chartHeight: Double, container: Pane) {
        this.chartHeight = chartHeight // Sets chartHeight for local use during init, needed to change later

        val maxVal = values.maxOrNull() ?: 1 // Get the highest value, to scale the graph
        bars = values.mapIndexed { index, value ->
            val height = (value.toDouble() / maxVal * chartHeight).coerceAtLeast(1.0) // Scales the height of the entire graph, with a minimum height of 1,0
            Rectangle(barWidth, height).apply {
                fill = Color.DARKSEAGREEN // Color the bars
                layoutX = index * barDistance  // Give the bars their X value based on index in the list
                layoutY = chartHeight - height // Fix to make the bars grow from the bottom up
            }
        }.toMutableList()

        container.children.addAll(bars) // Adds the bars to the Pane
    }

    /*
    Tracks the current algorithm and edits the graph accordingly
     */
    override fun track(i: Int,j: Int){
        Platform.runLater {
            val tempHeight = bars[i].height
            bars[i].height = bars[j].height //Swaps two bars

            bars[i].layoutY = chartHeight - bars[i].height //Realigns bar to the bottom

            bars[j].height = tempHeight
            bars[j].layoutY = chartHeight - bars[j].height //Realigns swapped bar to the bottom(fix)

            bars[i].fill = Color.ORANGE
            bars[j].fill = Color.ORANGE //Highlights bars to show where the sorting algorithm currently is
        }

        Thread.sleep(speedTime) //Pauses the process for a second so animation isn't too fast

        Platform.runLater {
            bars[i].fill = Color.DARKSEAGREEN
            bars[j].fill = Color.DARKSEAGREEN //Resets bar color
        }
    }
}