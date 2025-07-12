import nl.kittokazu.*
import kotlin.test.*

class UtilityTest {

    private val normalInput = "1,2,3,4,5"
    private val inputWithSpaces = "1 ,  2,3 , 4  , 5  " //User input with excess spaces, also acceptable
    private val normalArray = listOf(1,2,3,4,5)
    private val justASpace = " "
    private val emptyString = ""

    @Test
    fun testCheckIfUsable(){
        assertEquals(true,checkIfUsable(normalInput))
        assertEquals(true,checkIfUsable(inputWithSpaces))
        assertEquals(false,checkIfUsable(justASpace))
        assertEquals(false,checkIfUsable(emptyString))
    }

    @Test
    fun testConvertToList(){
        assertEquals(normalArray,convertToList(normalInput))
        assertEquals(normalArray,convertToList(inputWithSpaces))
        assertEquals(listOf(),convertToList(justASpace))
        assertEquals(listOf(),convertToList(emptyString))
    }

    /*
    Tests if .swap() can swap two indexes
     */
    @Test
    fun testSwapBasic(){
        val toSwap = intArrayOf(1,0)
        val toCheck = toSwap.copyOf()
        toSwap.swap(0,1)
        assertEquals(toCheck[1],toSwap[0])
    }

    /*
    Tests if .swap() can swap two chosen indexes
     */
    @Test
    fun testSwapFirstAndLast(){
        val toSwap = intArrayOf(1,2,3,4,5)
        val toCheck = toSwap.copyOf()
        toSwap.swap(0,toSwap.lastIndex)
        assertEquals(toCheck[0],toSwap[toSwap.lastIndex])
    }
}