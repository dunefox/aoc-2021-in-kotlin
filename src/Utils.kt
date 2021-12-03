import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: Int, small: Boolean = false) = File("src/Day$day", if (!small) "input.txt" else "input_small.txt").readLines()

fun linesToInt(lines: List<String>) = lines.filter { it != "" }.map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
