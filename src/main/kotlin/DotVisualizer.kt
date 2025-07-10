package nl.kittokazu

import javafx.application.Platform
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class DotVisualizer {
    private lateinit var circles: MutableList<Circle>

    fun initDots(values: IntArray, chartHeight: Double, container: Pane){
        val maxVal = values.maxOrNull() ?: 1

        circles = values.mapIndexed { index, value ->
            val x = index * 20.0 + 15.0  // spacing between bubbles
            val y = (value.toDouble() / maxVal * chartHeight)

            Circle(10.0).apply {
                centerX = x
                centerY = y
                fill = Color.CORNFLOWERBLUE
            }
        }.toMutableList()

        container.children.addAll(circles)
    }

    fun trackAlgorithm(i: Int, j: Int) {
        Platform.runLater {
            // Swap positions visually
            val tempY = circles[i].centerY
            circles[i].centerY = circles[j].centerY
            circles[j].centerY = tempY

            // Optional: highlight
            circles[i].fill = Color.ORANGE
            circles[j].fill = Color.ORANGE
        }

        Thread.sleep(speedTime)

        Platform.runLater {
            circles[i].fill = Color.CORNFLOWERBLUE
            circles[j].fill = Color.CORNFLOWERBLUE
        }
    }
}

