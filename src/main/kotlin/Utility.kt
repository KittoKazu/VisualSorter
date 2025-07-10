package nl.kittokazu

/*
Interface for making a class with a sorting algorithm, for clean code and to showcase inheritance.
 */
interface Sorter {
    fun sort(values: IntArray, onUpdate: (IntArray, Int, Int) -> Unit = { _, _, _ -> }) // Makes sure every Sorter has its own sort function, { _, _, _ -> } is a default value for testing.
}

/*
Function that swaps two variables in an array, used because both algorithms swap values.
 */
fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}