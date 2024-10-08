package com.app.sudokuapp

object SudokuLevels {
    val LEVEL_1_INITIAL = arrayOf(
        intArrayOf(5, 0, 4, 6, 7, 8, 9, 1, 2),
        intArrayOf(6, 7, 2, 1, 9, 5, 3, 4, 8),
        intArrayOf(1, 9, 8, 0, 4, 2, 5, 6, 7),
        intArrayOf(8, 5, 9, 7, 6, 1, 4, 0, 3),
        intArrayOf(4, 2, 6, 8, 5, 3, 7, 9, 1),
        intArrayOf(0, 1, 3, 9, 2, 4, 8, 5, 6),
        intArrayOf(9, 6, 1, 2, 3, 5, 0, 0, 7),
        intArrayOf(2, 8, 7, 4, 1, 9, 6, 3, 5),
        intArrayOf(3, 4, 0, 2, 8, 6, 1, 0, 9)
    )

    val LEVEL_1_SOLUTION = arrayOf(
        intArrayOf(5, 3, 4, 6, 7, 8, 9, 1, 2),
        intArrayOf(6, 7, 2, 1, 9, 5, 3, 4, 8),
        intArrayOf(1, 9, 8, 3, 4, 2, 5, 6, 7),
        intArrayOf(8, 5, 9, 7, 6, 1, 4, 2, 3),
        intArrayOf(4, 2, 6, 8, 5, 3, 7, 9, 1),
        intArrayOf(7, 1, 3, 9, 2, 4, 8, 5, 6),
        intArrayOf(9, 6, 1, 2, 3, 5, 4, 8, 7),
        intArrayOf(2, 8, 7, 4, 1, 9, 6, 3, 5),
        intArrayOf(3, 4, 5, 2, 8, 6, 1, 7, 9)
    )

    val LEVEL_2_INITIAL = arrayOf(
        intArrayOf(0, 0, 3, 0, 0, 8, 0, 0, 9),
        intArrayOf(2, 0, 0, 0, 3, 0, 0, 8, 0),
        intArrayOf(0, 0, 8, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 1, 0, 4, 0),
        intArrayOf(0, 0, 1, 0, 0, 0, 5, 0, 0),
        intArrayOf(0, 9, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 7, 0, 0, 0, 0, 0),
        intArrayOf(0, 6, 0, 0, 5, 0, 0, 0, 4),
        intArrayOf(4, 0, 0, 0, 0, 0, 2, 0, 0)
    )

    val LEVEL_2_SOLUTION = arrayOf(
        intArrayOf(4, 5, 3, 6, 2, 8, 1, 7, 9),
        intArrayOf(2, 1, 6, 9, 3, 7, 4, 8, 5),
        intArrayOf(7, 9, 8, 4, 1, 5, 6, 2, 3),
        intArrayOf(3, 8, 7, 5, 9, 1, 8, 4, 2),
        intArrayOf(9, 4, 1, 2, 8, 6, 5, 3, 7),
        intArrayOf(5, 2, 6, 3, 4, 7, 9, 1, 8),
        intArrayOf(1, 3, 4, 7, 6, 9, 8, 5, 2),
        intArrayOf(8, 6, 9, 1, 5, 2, 3, 2, 4),
        intArrayOf(4, 7, 2, 8, 9, 3, 2, 6, 1)
    )

    val LEVELS = listOf(
        Pair(LEVEL_1_INITIAL, LEVEL_1_SOLUTION),
        Pair(LEVEL_2_INITIAL, LEVEL_2_SOLUTION)
    )

    fun getLevel(level: Int): Pair<Array<IntArray>, Array<IntArray>> {
        return LEVELS[level - 1]
    }
}
