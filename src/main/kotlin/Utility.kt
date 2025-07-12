package nl.kittokazu
/*
Checks whether the string only contains usable characters. Returns True or False
 */
fun checkIfUsable(input: String): Boolean {
    input.ifBlank { return false } //Returns false if input is blank (or contains only spaces)
    val filter = Regex("^[\\d,\\s]*$") //regex expression filters out d(digits), commas and s(whitespaces)
    return input.matches(filter)
}

/*
Converts a string into a list, ignoring spaces.
 */
fun convertToList(input:String): List<Int> {
    return input
        .replace(" ", "") //Removes all spaces
        .split(",") //Filters values seperated by comma
        .filter{it.isNotEmpty()} //Filters out empty strings
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