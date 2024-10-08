package com.app.sudokuapp

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import com.app.sudokuapp.databinding.ActivityGameBinding
import android.view.ViewGroup.LayoutParams
import android.view.WindowManager
import com.app.sudokuapp.databinding.PopupCompletionBinding
import android.graphics.Color
import android.util.Log


class GameActivity : ComponentActivity() {

    private var solution: Array<IntArray>? = null
    private var puzzle: Array<IntArray>? = null

    private val binding: ActivityGameBinding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }
    private var selectedCell: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val level = intent.getIntExtra("LEVEL", 1)

        val (puzzle, solution) = SudokuLevels.getLevel(level)
        this.puzzle = puzzle
        this.solution = solution

        setupSudokuGrid(puzzle)
        setupNumberSelection()

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, LevelSelectionActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnHint.setOnClickListener {
            provideHint(puzzle)
        }
        binding.btnReset.setOnClickListener {
            showCompletionPopup()
        }
    }

    private fun setupSudokuGrid(puzzle: Array<IntArray>) {
        for (i in 0 until binding.gridSudoku.childCount) {
            val cellButton = binding.gridSudoku.getChildAt(i) as Button
            val row = i / 9
            val col = i % 9

            val cellValue = puzzle[row][col]
            if (cellValue != 0) {
                cellButton.text = cellValue.toString()
                cellButton.isEnabled = false
            } else {
                cellButton.text = ""
                cellButton.isEnabled = true
            }

            cellButton.setOnClickListener {
                highlightSelectedCell(cellButton) // เน้น cell ที่เลือก
            }
        }
    }

    private fun highlightSelectedCell(selectedButton: Button) {
        selectedCell?.setBackgroundResource(R.drawable.shape_background_cell_normal)

        selectedButton.setBackgroundResource(R.drawable.shape_background_cell_highlight)

        selectedCell = selectedButton
    }

    private fun provideHint(puzzle: Array<IntArray>) {
        val emptyCells = mutableListOf<Int>()

        for (i in 0 until binding.gridSudoku.childCount) {
            val row = i / 9
            val col = i % 9
            if (puzzle[row][col] == 0) {
                emptyCells.add(i)
            }
        }

        if (emptyCells.isNotEmpty()) {
            val randomIndex = kotlin.random.Random.nextInt(emptyCells.size)
            val randomCellIndex = emptyCells[randomIndex]
            val row = randomCellIndex / 9
            val col = randomCellIndex % 9

            solution?.let {
                val correctNumber = it[row][col]
                val selectedButton = binding.gridSudoku.getChildAt(randomCellIndex) as Button

                highlightSelectedCell(selectedButton)

                selectedButton.text = correctNumber.toString()
                selectedButton.isEnabled = false
                puzzle[row][col] = correctNumber
            }

        } else {
            Toast.makeText(this, "No empty cells available for a hint!", Toast.LENGTH_SHORT).show()
        }
        if (isGameComplete()) {
            showCompletionPopup()
        }
    }


    private fun isGameComplete(): Boolean {
        puzzle?.let { currentPuzzle ->
            for (i in currentPuzzle.indices) {
                for (j in currentPuzzle[i].indices) {
                    if (currentPuzzle[i][j] == 0) {
                        return false
                    }
                }
            }
            return true

        }
        return false
    }


    private fun checkCellAnswer(row: Int, col: Int, number: Int) {
        solution?.let {
            if (it[row][col] == number) {
                selectedCell?.text = number.toString()
                selectedCell?.isEnabled = false // เมื่อใส่ตัวเลขที่ถูกต้องจะปิดการแก้ไข
            } else {
                Toast.makeText(this, "Wrong number!", Toast.LENGTH_SHORT).show()
            }
        }

        if (isGameComplete()) {
            showCompletionPopup()
        }
    }

    private fun updateSelectedCell(number: Int) {
        selectedCell?.let {
            val index = binding.gridSudoku.indexOfChild(it)
            val row = index / 9
            val col = index % 9
            checkCellAnswer(row, col, number)
        } ?: run {
            Toast.makeText(this, "Please select a cell first!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupNumberSelection() {
        val numbers = arrayOf(
            binding.optionNumber1,
            binding.optionNumber2,
            binding.optionNumber3,
            binding.optionNumber4,
            binding.optionNumber5,
            binding.optionNumber6,
            binding.optionNumber7,
            binding.optionNumber8,
            binding.optionNumber9
        )

        for (i in numbers.indices) {
            numbers[i].setOnClickListener {
                updateSelectedCell(i + 1)
            }
        }
    }

    private fun showCompletionPopup() {
        Log.d("GameActivity", "Popup created.")
        val popupViewBinding = PopupCompletionBinding.inflate(LayoutInflater.from(this))

        val popupWindow = PopupWindow(
            popupViewBinding.root,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            true
        )

        val level = intent.getIntExtra("LEVEL", 1)

        popupViewBinding.tvCompletionMessage.text = "Level $level Complete!"

        popupViewBinding.btnNextLevel.setOnClickListener {
            val nextLevelIntent = Intent(this, GameActivity::class.java)
            nextLevelIntent.putExtra("LEVEL", level + 1)
            startActivity(nextLevelIntent)
            popupWindow.dismiss()
            finish()
        }

        popupViewBinding.btnCancel.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
    }


}
