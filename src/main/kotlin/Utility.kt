package nl.kittokazu
/*
Checks whether the string only contains usable characters. Returns True or False
 */
fun checkIfUsable(input: String): Boolean {
    val filter = Regex("^[\\d,\\s]*$") //regex expression filters out d(digits), commas and s(whitespaces)
    return input.matches(filter)
}

/*
Converts a string into a list, ignoring spaces.
 */
fun convertToList(input:String): List<Int> {
    return input
        .split(",") //Filters values seperated by comma
        .map {it.trim()}.filter{it.isNotEmpty()} //Filters out spaces before and after values
        .map {it.toInt()} //Turns the values to Int
}
/*
Function that swaps two variables in an array, used because both algorithms swap values.
 */
fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}