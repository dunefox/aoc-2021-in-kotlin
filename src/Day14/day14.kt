import java.lang.Exception
import java.math.BigInteger
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun day14() {
    fun part1(input: List<String>): Int {
        val polymer = input.first()
        val instr = input.drop(1).map { it.split(" -> ") }.associate { it[0] to it[1] }
        var result = polymer.zipWithNext().joinToString(separator = "") {
            it.first.toString() + instr[it.first.toString().plus(it.second.toString())]
        }.plus(polymer.last())

        for (i in 2..10) {
            result = result.zipWithNext().joinToString(separator = "") {
                it.first.toString() + instr[it.first.toString().plus(it.second.toString())]
            }.plus(polymer.last())
        }

        val counts = result.groupBy { it }.map { it.key to it.value.size }

        return counts.maxOf { it.second } - counts.minOf { it.second }
    }

    fun part2(input: List<String>): Long {
        return 1
    }

    val testInput = readInput(14, true)
    val input = readInput(14)

    check(part1(testInput) == 1588)
    println("\tPart 1: ${part1(input)}")
//    check(part2(testInput) == 2188189693529)
//    println("\tPart 2: ${part2(input)}")
}
