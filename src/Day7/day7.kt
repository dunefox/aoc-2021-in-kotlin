import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
fun day7() {
    fun gauss(x: Int): Int {
        return ((x * x) + x) / 2
    }

    fun part1(input: List<Int>): Int {
        var min = Int.MAX_VALUE

        for (crab in input) {
            var tmp = 0

            for (other_crab in input) {
                tmp += kotlin.math.abs(crab - other_crab)
            }

            if (tmp < min) {
                min = tmp
            }
        }

        return min
    }

    fun part2(input: List<Int>): Int {
        // Part 1 and 2 are identical except for `gauss` but it's great how concise Kotlin can be :^)
        // This could be condensed into one higher-order function with `id` for part 1 and `gauss` for part 2 as parameter
        return input.map { input.map { it2 -> gauss(kotlin.math.abs(it - it2 + 1)) } }.minOf { it.sum() }
    }

    val testInput = linesToIntSequence(readInput(7, true)) { it.split(",") }
    val input = linesToIntSequence(readInput(7)) { it.split(",") }

    check(part1(testInput.first()) == 37)
    println("\tPart 1: ${part1(input.first())}")
    check(part2(testInput.first()) == 168)
    println("\tPart 2: ${part2(input.first())}")
}