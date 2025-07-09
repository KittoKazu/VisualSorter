package nl.kittokazu

import javafx.application.Platform

fun bubbleSortVisual(values: IntArray, bars: MutableList<javafx.scene.shape.Rectangle>) {
    for (i in 0 until values.size - 1) {
        for (j in 0 until values.size - i - 1) {
            // Highlight compared bars
            Platform.runLater {
                bars[j].fill = javafx.scene.paint.Color.ORANGE
                bars[j + 1].fill = javafx.scene.paint.Color.ORANGE
            }

            Thread.sleep(speedTime)

            if (values[j] > values[j + 1]) {
                // Swap values
                val temp = values[j]
                values[j] = values[j + 1]
                values[j + 1] = temp

                // Swap bar heights
                Platform.runLater {
                    val heightJ = bars[j].height
                    bars[j].height = bars[j + 1].height
                    bars[j + 1].height = heightJ
                }
            }

            // Reset color
            Platform.runLater {
                bars[j].fill = javafx.scene.paint.Color.DARKSEAGREEN
                bars[j + 1].fill = javafx.scene.paint.Color.DARKSEAGREEN
            }

            Thread.sleep(speedTime)
        }
    }
}

fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}