fun day2() {
    fun part1(input: List<String>): Int {
        var x = 0
        var y = 0

        for (line in input) {
            val (cmd, num) = line.split(" ")

            when (cmd) {
                "forward" -> x += num.toInt()
                "down"    -> y += num.toInt()
                "up"      -> y -= num.toInt()
            }
        }

        return x * y
    }

    fun part2(input: List<String>): Int {
        var x = 0
        var y = 0
        var aim = 0

        for (line in input) {
            val (cmd, num) = line.split(" ")

            when (cmd) {
                "forward" -> {
                    x += num.toInt()
                    y += aim * num.toInt()
                }
                "down"    -> aim += num.toInt()
                "up"      -> aim -= num.toInt()
            }
        }

        return x * y
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(2, true)
    val input = readInput(2)
    check(part1(testInput) == 150)
    println("\tPart 1: ${part1(input)}")

    check(part2(testInput) == 900)
    println("\tPart 2: ${part2(input)}")
}
