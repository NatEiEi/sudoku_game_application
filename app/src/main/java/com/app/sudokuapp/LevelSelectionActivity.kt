package com.app.sudokuapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.app.sudokuapp.databinding.ActivityGameBinding
import com.app.sudokuapp.databinding.ActivityLevelSelectionBinding

class LevelSelectionActivity : ComponentActivity() {
    private val binding: ActivityLevelSelectionBinding by lazy {
        ActivityLevelSelectionBinding.inflate(layoutInflater)
    }

    private fun setupLevelSelectionGrid() {
        for (i in 0 until binding.gridLevelSelection.childCount) {
            val levelButton = binding.gridLevelSelection.getChildAt(i) as Button
            levelButton.setOnClickListener {
                startGameActivity(i + 1)
            }
        }
    }

    private fun startGameActivity(level: Int) {
        val intent = Intent(this, GameActivity::class.java).apply {
            putExtra("LEVEL", level)
        }
        startActivity(intent)
    }


    private fun setupView() {

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        setupLevelSelectionGrid()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        setupView()


    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}