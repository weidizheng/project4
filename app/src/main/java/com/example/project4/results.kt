package com.example.project4

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
    private var difficultyLevel: String = "easy"
    private var mathOperation: String = "addition"
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

        // Display the quiz results
        displayResults()
    }

    // Function to display results in the TextView
    private fun displayResults() {
        // Calculate total answered questions
        totalQuestions = correctAnswers + wrongAnswers

        // Prepare result strings
        val correctText = "Correct: $correctAnswers"
        val incorrectText = "Incorrect: $wrongAnswers"
        val scoreText = "Score: $correctAnswers/$totalQuestions"

        // Display results as formatted text
        resultText.text = "$correctText\n$incorrectText\n$scoreText"
    }
}
