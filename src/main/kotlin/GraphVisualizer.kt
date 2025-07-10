package nl.kittokazu

import javafx.application.Platform
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class GraphVisualizer {

    lateinit var bars: MutableList<Rectangle>

    fun initGraph(values: IntArray, chartHeight: Double,container: javafx.scene.layout.HBox) {

        val maxVal = values.maxOrNull() ?: 1 // Get the highest value, to scale the graph
        bars = values.map { value ->
            val height = (value.toDouble() / maxVal * chartHeight).coerceAtLeast(1.0) // Scales the entire chart
            Rectangle(10.0, height).apply {
                fill = Color.DARKSEAGREEN //create and color the bars of the graph
            }
        }.toMutableList()

        container.children.addAll(bars)
    }

    fun trackAlgorithm(i: Int,j: Int){
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
}