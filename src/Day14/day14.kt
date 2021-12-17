import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

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
        val polymer = input.first()
        val instr = input.drop(1).map { it.split(" -> ") }.associate { it[0] to it[1] }
        var counts: MutableMap<String, Long> = instr.keys.associateWith { 0.toLong() }.toMutableMap()
        val charCounts: MutableMap<String, Long> = instr.map { it.value }.distinct().associateWith { 0.toLong() }.toMutableMap()

        val result = polymer.zipWithNext().map {
            it.first.toString() + it.second.toString()
        }.groupBy { it }.map { it.key to it.value.size }.toMap()

        for ((key, value) in result) {
            counts[key] = value.toLong()
        }

        for (c in polymer.toList()) {
            charCounts[c.toString()] = charCounts[c.toString()]!! + 1
        }

        for (step in 1..40) {
            val newCounts: MutableMap<String, Long> = counts.keys.associateWith { 0.toLong() }.toMutableMap()

            for (key in counts.keys.filter { counts[it]!! > 0 }) {
                val leftBigram = key[0] + instr[key]!!
                val rightBigram = instr[key]!! + key[1]
                newCounts[rightBigram] = counts[key]!! + newCounts[rightBigram]!!
                newCounts[leftBigram] = counts[key]!! + newCounts[leftBigram]!!
                charCounts[instr[key]!!] = charCounts[instr[key]!!]!! + counts[key]!!
                counts[key] = 0 // The old bigram ceases to exist
            }

            counts = newCounts
        }

        return charCounts.maxOf { it.value } - charCounts.minOf { it.value }
    }

    val testInput = readInput(14, true)
    val input = readInput(14)

    check(part1(testInput) == 1588)
    println("\tPart 1: ${part1(input)}")
    check(part2(testInput) == 2188189693529)
    println("\tPart 2: ${part2(input)}")
}

