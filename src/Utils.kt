import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: Int, small: Boolean = false) = File("src/Day$day", if (!small) "input.txt" else "small_input.txt").readLines()

fun linesToInt(lines: List<String>) = lines.filter { it != "" }.map { it.toInt() }
fun linesToIntSequence(lines: List<String>, splitBy: (String) -> List<String>) = lines.filter { it != "" }.map { it -> splitBy(it).map { it.toInt() } }
fun linesToSequence(lines: List<String>, splitBy: (String) -> List<String>) = lines.filter { it != "" }.map { splitBy(it) }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun printSolution(day: Int, part1: Int, part2: Int, time1: Int, time2: Int) {
    println("Running day $day")
    println("\t$part1 took $time1")
    println("\t$part2 took $time2")
}
