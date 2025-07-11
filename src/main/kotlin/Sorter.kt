package nl.kittokazu
/*
Interface for making a class with a sorting algorithm, for clean code and to showcase inheritance.
 */
interface Sorter {
    fun sort(values: IntArray, onUpdate: (IntArray, Int, Int) -> Unit = { _, _, _ -> }) // Makes sure every Sorter has its own sort function, { _, _, _ -> } is a default value for testing.
}