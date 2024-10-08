package com.app.sudokuapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.app.sudokuapp.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var level = 1

    private fun setupView() {
        binding.btnStartGame.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java).apply {
                putExtra("LEVEL", level)
            }
            startActivity(intent)
        }

        binding.btnSelectLevel.setOnClickListener {
            val intent = Intent(this, LevelSelectionActivity::class.java)
            startActivity(intent)
        }

        binding.btnExit.setOnClickListener {
            finishAffinity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
