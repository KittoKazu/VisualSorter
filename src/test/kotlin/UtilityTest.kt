import nl.kittokazu.*
import kotlin.test.*

class UtilityTest {

    private val normalInput = "1,2,3,4,5"
    private val wrongInput = "A,B,C,4,5"
    private val inputWithSpaces = "1 ,  2,3 , 4  , 5  " //User input with excess spaces, also acceptable
    private val normalArray = listOf(1,2,3,4,5)
    private val justASpace = " "
    private val emptyString = ""

    @Test
    fun testCheckIfUsable(){
        assertEquals(true,checkIfUsable(normalInput),"Correct input not flagged usable")
        assertEquals(false,checkIfUsable(wrongInput),"Incorrect input flagged usable")
        assertEquals(true,checkIfUsable(inputWithSpaces),"Excess spaces not flagged usable")
        assertEquals(false,checkIfUsable(justASpace),"String with just space deemed usable")
        assertEquals(false,checkIfUsable(emptyString),"Empty string flagged usable")
    }

    @Test
    fun testConvertToList(){
        assertEquals(normalArray,convertToList(normalInput),"Array not converted to list")
        assertEquals(normalArray,convertToList(inputWithSpaces),"Spaces not filtered out correctly when converting to list")
        assertEquals(listOf(),convertToList(justASpace),"Full list appears when converting space to list")
        assertEquals(listOf(),convertToList(emptyString),"Full list appears when converting empty string to list")
    }

    /*
    Tests if .swap() can swap two indexes
     */
    @Test
    fun testSwapBasic(){
        val toSwap = intArrayOf(1,0)
        val toCheck = toSwap.copyOf()
        toSwap.swap(0,1)
        assertEquals(toCheck[1],toSwap[0],"swap does not correctly swap values")
    }

    /*
    Tests if .swap() can swap two chosen indexes, in the testcase this is the first and last entry
     */
    @Test
    fun testSwapFirstAndLast(){
        val toSwap = intArrayOf(1,2,3,4,5)
        val toCheck = toSwap.copyOf()
        toSwap.swap(0,toSwap.lastIndex)
        assertEquals(toCheck[0],toSwap[toSwap.lastIndex],"swap does not swap designated indexes")
    }
}