package nl.kittokazu

class BubbleSort : Sorter {
    override fun sort(values: IntArray, onUpdate: (values: IntArray, i: Int, j: Int) -> Unit) {
        for (i in 0 until values.size - 1) {

            for (j in 0 until values.size - i - 1) {

                if (values[j] > values[j + 1]) {
                    values.swap(j, j + 1)
                    onUpdate(values, j, j + 1)
                }
            }
        }
    }
}