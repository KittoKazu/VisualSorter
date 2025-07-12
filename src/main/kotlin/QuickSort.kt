package nl.kittokazu

/*
Starts the sorter and calls the recursive function for the first time
 */
class QuickSort : Sorter {
    override fun sort(values: IntArray, onUpdate: (values: IntArray, i: Int, j: Int) -> Unit) {
        quickSortRecursive(values, 0, values.size -1, onUpdate)
    }

/*
Divides an array into partitions, recursively
 */
    private fun quickSortRecursive(
        values: IntArray,
        low: Int,
        high: Int,
        onUpdate: (arr: IntArray, i: Int, j: Int) -> Unit
    ) {
        if (low < high) {
            val pivotIndex = partition(values, low, high, onUpdate) //Calls for partition to sort around pivot
            quickSortRecursive(values, low, pivotIndex - 1, onUpdate) //Calls itself left of pivot
            quickSortRecursive(values, pivotIndex + 1, high, onUpdate) //Calls itself right of pivot
        }
    }


    /*
    Sorts a partition created by the recursive function
     */
    private fun partition(
        values: IntArray,
        low: Int,
        high: Int,
        onUpdate: (arr: IntArray, i: Int, j: Int) -> Unit): Int {
        val pivot = values[high]
        var i = low - 1

        for (j in low until high) {
            if (values[j] <= pivot) {
                i++
                values.swap(i, j) //Puts values lower than pivot on the left
                onUpdate(values, i, j)  // Notifies visualisation
            }
        }

        values.swap(i + 1, high) //Final swap, puts pivot in correct spot
        onUpdate(values, i + 1, high)  // Notifies Visualisation
        return i + 1
    }
}