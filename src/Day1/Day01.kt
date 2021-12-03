fun day1() {
    fun part1(input: List<Int>): Int {
        var count = 0

        for ((l, r) in input.zipWithNext()) {
            if (r - l > 0) count += 1
        }

        return count
    }

    fun part2(input: List<Int>): Int {
        var count = 0

        for (i in 3 until input.size) {
            if (input[i] > input[i - 3]) count += 1
        }

        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = linesToInt(readInput(1, true))
    val input = linesToInt(readInput(1))
    check(part1(testInput) == 7)
    println("\tPart 1: ${part1(input)}")

    check(part2(testInput) == 5)
    println("\tPart 2: ${part2(input)}")
}
