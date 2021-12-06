import java.math.BigInteger

fun day6() {
    fun part1(input: List<Int>): Int {
        val fishies = generateSequence { 0 }.take(9).toMutableList()

        // Day 1: initialise population
        for (fish in input) {
            fishies[fish] += 1
        }

        for (day in 1..80) {
            val zero = fishies.removeFirst()
            // New fishies spawned for each fishy in fishies[0]
            fishies.add(zero)
            // Each fishy in 0 goes to fishies[6] :P
            fishies[6] += zero
        }

        return fishies.sum()
    }

    fun part2(input: List<Int>): BigInteger {
        val fishies = generateSequence { 0 }.take(9).map { it.toBigInteger() }.toMutableList()

        for (fish in input) {
            fishies[fish] = fishies[fish] + 1.toBigInteger()
        }

        for (day in 1..256) {
            val zero = fishies.removeFirst()
            fishies.add(zero)
            fishies[6] += zero
        }

        var sum = 0.toBigInteger()

        for (fishy in fishies) {
            sum += fishy
        }

        return sum
    }

    val testInput = linesToIntSequence(readInput(6, true)) { it.split(",") }
    val input = linesToIntSequence(readInput(6)) { it.split(",") }

    check(part1(testInput.first()) == 5934)
    println("\tPart 1: ${part1(input.first())}")
    check(part2(testInput.first()) == 26984457539.toBigInteger())
    println("\tPart 2: ${part2(input.first())}")
}