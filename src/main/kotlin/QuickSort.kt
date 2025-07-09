package nl.kittokazu

import javafx.application.Platform

fun quickSortVisual(arr: IntArray, bars: MutableList<javafx.scene.shape.Rectangle>) {
    quickSortRecursive(arr, bars, 0, arr.lastIndex)
}

private fun quickSortRecursive(arr: IntArray, bars: MutableList<javafx.scene.shape.Rectangle>, low: Int, high: Int) {
    if (low < high) {
        val pi = partition(arr, bars, low, high)
        quickSortRecursive(arr, bars, low, pi - 1)
        quickSortRecursive(arr, bars, pi + 1, high)
    }

}

private fun partition(arr: IntArray, bars: MutableList<javafx.scene.shape.Rectangle>, low: Int, high: Int): Int {
    val pivot = arr[high]

    Platform.runLater {
        bars[high].fill = javafx.scene.paint.Color.PURPLE // Pivot color
    }

    var i = low - 1

    for (j in low until high) {
        Platform.runLater {
            bars[j].fill = javafx.scene.paint.Color.ORANGE // Comparison color
        }
        Thread.sleep(speedTime)

        if (arr[j] < pivot) {
            i++
            if (i != j) {
                arr.swap(i, j)

                Platform.runLater {
                    val tempHeight = bars[i].height
                    bars[i].height = bars[j].height
                    bars[j].height = tempHeight
                }
                Thread.sleep(speedTime)
            }
        }

        Platform.runLater {
            bars[j].fill = javafx.scene.paint.Color.DARKSEAGREEN
        }
    }

    // Swap pivot to its correct position
    arr.swap(i + 1, high)

    Platform.runLater {
        val tempHeight = bars[i + 1].height
        bars[i + 1].height = bars[high].height
        bars[high].height = tempHeight
    }

    Thread.sleep(speedTime)

    Platform.runLater {
        bars[high].fill = javafx.scene.paint.Color.DARKSEAGREEN
    }

    return i + 1
}