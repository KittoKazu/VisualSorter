/*
Class with basic arrays to test sorting algorithms with, can be inherited from.
 */
open class TestSorter {
    protected val basicTestingList = IntArray(5) {it + 1}
    protected val longTestingList = IntArray(100) {it + 1}
    protected val dupeTestingList = IntArray(5) {1}
    protected val emptyTestingList = intArrayOf()
}