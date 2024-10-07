package com.example.project4

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // second result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val correctAnswers = data?.getIntExtra("correctAnswers", 0) ?: 0
            val totalQuestions = data?.getIntExtra("totalQuestions", 0) ?: 0
            val correctPercentage = (correctAnswers * 100) / totalQuestions

            // Change color base on rating
            val resultTextView: TextView = findViewById(R.id.result)
            if (correctPercentage >= 80) {
                resultTextView.text = "Congratulations! You got $correctPercentage% correct."
                resultTextView.setTextColor(Color.GREEN)
            } else {
                resultTextView.text = "You got only $correctPercentage% correct. Try Again!"
                resultTextView.setTextColor(Color.RED)
            }
        }
    }
}
