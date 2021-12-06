fun day3() {
    fun buildByte(current: String, counts: List<Pair<Int, Pair<Int, Int>>>, cmp: (Int, Int) -> Boolean): Int {
        return if (counts.isEmpty()) {
            return Integer.parseInt(current, 2)
        } else {
            val bit = if (cmp(counts.first().second.first, counts.first().second.second)) "0" else "1"
            buildByte(current + bit, counts.drop(1), cmp)
        }
    }

    fun countBits(lines: List<String>): Map<Int, Pair<Int, Int>> {
        val counts = mutableMapOf<Int, Pair<Int, Int>>()

        for (line in lines) {
            for ((pos, bit) in line.withIndex()) {
                val current = counts.getOrDefault(pos, Pair(0, 0))

                if (bit == '0') counts[pos] = current.copy(first = current.first + 1)
                else  counts[pos] = current.copy(second = current.second + 1)
            }
        }

        return counts
    }

    fun part1(input: List<String>): Int {
        val counts = countBits(input)

        // The binary string could just as well be flipped, but I wanted to use a higher-order function and a lambda in kotlin :^)
        return buildByte("", counts.toList()) { a: Int, b: Int -> a > b } * buildByte("", counts.toList()) { a: Int, b: Int -> a < b }
    }

    tailrec fun searchByteSpace(bytes: List<String>, current_pos: Int, counts: Map<Int, Pair<Int, Int>>, zero: Char = '0', one: Char = '1'): String {
        val c = if (counts[current_pos]!!.first <= counts[current_pos]!!.second) one else zero
        val remaining = bytes.filter { it[current_pos] == c }

        return when (remaining.size) {
            1 -> remaining.first()
            else -> searchByteSpace(remaining, current_pos + 1, countBits(remaining), zero, one)
        }
    }

    fun part2(input: List<String>): Int {
        val allcounts = countBits(input)
        val oxygen = searchByteSpace(input, 0, allcounts)
        val co2 = searchByteSpace(input, 0, allcounts, '1', '0')

        return oxygen.toInt(2) * co2.toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(3, true)
    val input = readInput(3)

    check(part1(testInput) == 198)
    println("\tPart 1: ${part1(input)}")
    check(part2(testInput) == 230)
    println("\tPart 2: ${part2(input)}")
}
