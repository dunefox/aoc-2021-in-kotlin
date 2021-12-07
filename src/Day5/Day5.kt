package Day5

import linesToIntSequence
import readInput

fun day5() {
    fun part1(input: List<List<Int>>): Int {


        return 1
    }

    fun part2(input: List<List<Int>>): Int {
        return 1
    }

    val testInput = linesToIntSequence(readInput(5, true)) { it.split("(,)|( -> )".toRegex()) }
    val input = linesToIntSequence(readInput(5)) { it.split("(,)|( -> )".toRegex()) }

    check(part1(testInput) == 5)
    println("\tPart 1: ${part1(input)}")
    check(part2(testInput) == 12)
    println("\tPart 2: ${part2(input)}")
}
