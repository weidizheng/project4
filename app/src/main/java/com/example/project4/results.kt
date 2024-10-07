package com.example.project4

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class results : Fragment() {

    // UI Components
    private lateinit var retryButton: Button
    private lateinit var resultText: TextView

    // State variables for tracking quiz progress
    private var totalQuestions: Int = 10
    private var correctAnswers: Int = 0
    private var wrongAnswers: Int = 0

    // Inflate the layout and set up arguments
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for the results screen
        val rootView = inflater.inflate(R.layout.fragment_results, container, false)

        // Retrieve passed arguments (correct and incorrect answers)
        correctAnswers = questionsArgs.fromBundle(requireArguments()).correctAnswers
        wrongAnswers = questionsArgs.fromBundle(requireArguments()).incorrectAnswers

        // Set up UI elements
        resultText = rootView.findViewById(R.id.result)
        retryButton = rootView.findViewById(R.id.btn_try_again)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle click event for retry button to navigate to Welcome screen
        retryButton.setOnClickListener {
            val navigationAction = resultsDirections.actionResultsToWelcome()
            view.findNavController().navigate(navigationAction)
        }

        // Display results
        displayResults()
    }

    // Function to display results in the TextView
    private fun displayResults() {
        // Calculate total answered questions
        totalQuestions = correctAnswers + wrongAnswers

        if (totalQuestions != 0) {
            // Calculate correct percentage
            val correctPercentage = (correctAnswers * 100) / totalQuestions

            // Prepare result strings based on score
            val resultMessage = if (correctPercentage >= 80) {
                // display congrats message in green
                resultText.setTextColor(Color.GREEN)
                "Congratulations! You got $correctPercentage% correct.\n" +
                        "Score: $correctAnswers/$totalQuestions"
            } else {
                //  display fail message in red
                resultText.setTextColor(Color.RED)
                "You got only $correctPercentage% correct. Try Again!\n" +
                        "Score: $correctAnswers/$totalQuestions"
            }

            resultText.text = resultMessage
        } else {
            resultText.text = "No questions answered."
        }
    }
}
