package nl.kittokazu

import javafx.application.Platform
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class GraphVisualizer {

    private lateinit var bars: MutableList<Rectangle>
    private var chartHeight = 0.0

    fun initGraph(values: IntArray, chartHeight: Double,container: javafx.scene.layout.Pane) {
        this.chartHeight = chartHeight // Sets chartHeight for local use during init, needed to change later.

        val maxVal = values.maxOrNull() ?: 1 // Get the highest value, to scale the graph.
        bars = values.mapIndexed { index, value ->
            val height = (value.toDouble() / maxVal * chartHeight).coerceAtLeast(1.0) // Scales the entire chart.
            Rectangle(10.0, height).apply {
                fill = Color.DARKSEAGREEN // Color the bars.
                layoutX = index * 12.0  // Give the bars their X value based on index in the list.
                layoutY = chartHeight - height // Fix to make the bars grow from the bottom up.
            }
        }.toMutableList()

        container.children.addAll(bars) // Adds the bars to the Pane.
    }

    fun trackAlgorithm(i: Int,j: Int){
        Platform.runLater {
            val tempHeight = bars[i].height
            bars[i].height = bars[j].height
            bars[i].layoutY = chartHeight - bars[i].height

            bars[j].height = tempHeight
            bars[j].layoutY = chartHeight - bars[j].height

            bars[i].fill = Color.ORANGE
            bars[j].fill = Color.ORANGE
        }
        Thread.sleep(speedTime)
        Platform.runLater {
            bars[i].fill = Color.DARKSEAGREEN
            bars[j].fill = Color.DARKSEAGREEN
        }
    }
}