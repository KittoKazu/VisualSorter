package nl.kittokazu

class QuickSort : Sorter {
    override fun sort(values: IntArray, onUpdate: (values: IntArray, i: Int, j: Int) -> Unit) {
        quickSortRecursive(values, 0, values.size -1, onUpdate)
    }


    private fun quickSortRecursive(
        values: IntArray,
        low: Int,
        high: Int,
        onUpdate: (arr: IntArray, i: Int, j: Int) -> Unit
    ) {
        if (low < high) {
            val pivotIndex = partition(values, low, high, onUpdate)
            quickSortRecursive(values, low, pivotIndex - 1, onUpdate)
            quickSortRecursive(values, pivotIndex + 1, high, onUpdate)
        }
    }


    private fun partition(
        values: IntArray,
        low: Int,
        high: Int,
        onUpdate: (arr: IntArray, i: Int, j: Int) -> Unit
    ): Int {
        val pivot = values[high]
        var i = low - 1

        for (j in low until high) {
            if (values[j] <= pivot) {
                i++
                values.swap(i, j)
                onUpdate(values, i, j)  // Notify for visualization
            }
        }

        values.swap(i + 1, high)
        onUpdate(values, i + 1, high)  // Final pivot swap
        return i + 1
    }
}