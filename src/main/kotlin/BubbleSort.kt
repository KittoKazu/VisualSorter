package nl.kittokazu

class BubbleSort : Sorter {

    /*
    Sorts an array using bubble sort
     */
    override fun sort(values: IntArray, onUpdate: (values: IntArray, i: Int, j: Int) -> Unit) {
        for (i in 0 until values.size - 1) { //Loop through the array

            for (j in 0 until values.size - i - 1) { //Loop per value to find a bigger value

                if (values[j] > values[j + 1]) {
                    values.swap(j, j + 1) //Swaps two values when it finds a bigger value, moving the bigger value to the right
                    onUpdate(values, j, j + 1) //Notifies visualisation
                }
            }
        }
    }
}