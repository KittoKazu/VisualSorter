import nl.kittokazu.*
import kotlin.test.*

class BubbleSortTest : TestSorter() {

    /*
    Tests Whether a basic array sorts correctly
    */
    @Test
    fun bubbleSortBasicTest(){
        val toSort = basicTestingList.copyOf() //copyOf so original array cannot be touched
        toSort.shuffle()
        BubbleSort().sort(toSort)
        assertContentEquals(basicTestingList, toSort)
    }

    /*
    Tests whether a long array (99+ indexes) sorts correctly
    */
    @Test
    fun bubbleSortLongTest(){
        val toSort = longTestingList.copyOf()
        toSort.shuffle()
        BubbleSort().sort(toSort)
        assertContentEquals(longTestingList, toSort)
    }

    /*
    Tests whether an array of duplicates sorts correctly
     */
    @Test
    fun bubbleSortDupeTest(){
        val toSort = dupeTestingList.copyOf()
        toSort.shuffle()
        BubbleSort().sort(toSort)
        assertContentEquals(dupeTestingList, toSort)
    }

    /*
    Tests whether an empty array sorts correctly
     */
    @Test
    fun bubbleSortEmptyTest(){
        val toSort = emptyTestingList.copyOf()
        toSort.shuffle()
        BubbleSort().sort(toSort)
        assertContentEquals(emptyTestingList, toSort)
    }

    /*
    Tests whether the onUpdate functionality works while sorting a simple list
     */
    @Test
    fun onUpdateTest(){
        val toSort = basicTestingList.copyOf()
        var isUpdated = false

        toSort.shuffle()
        BubbleSort().sort(toSort) { values, i, j ->
            isUpdated = true //Sets to true if onUpdate is called at least once
        }

        assertEquals(true, isUpdated)
        assertContentEquals(basicTestingList, toSort)
    }
}