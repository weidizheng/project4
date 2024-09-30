package com.example.project4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlin.random.Random

class questions : Fragment() {

    private lateinit var btnDone: Button
    private lateinit var mathQuestionTextView: TextView
    private lateinit var etUserAnswer: EditText

    // Selected difficulty level (easy, medium, or hard).
    var difficulty = "easy"

    // Chosen math operation (addition, subtraction, etc.).
    var operation = "addition"

    // Total number of questions for the quiz.
    var numberOfQuestions: Int = 10

    // Holds the correct answer for the current question.
    var currentAnswer: Int = 0

    // Counters for correct and incorrect answers.
    var totalCorrectAnswers: Int = 0
    var totalWrongAnswers: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_questions, container, false)
        difficulty = welcomeArgs.fromBundle(requireArguments()).difficulty
        operation = welcomeArgs.fromBundle(requireArguments()).operation
        numberOfQuestions = welcomeArgs.fromBundle(requireArguments()).questionAmount

        // Set up the UI
        mathQuestionTextView = view.findViewById(R.id.math_question)
        etUserAnswer = view.findViewById(R.id.et_user_answer)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up button and click listener for checking answers.
        btnDone = view.findViewById(R.id.btn_done)
        btnDone.setOnClickListener {
            checkAnswer()
        }

        // Display the first math question.
        setRandomMathProblem()
    }

    private fun generateRandomProblem(operation: String, difficulty: String): String {
        // Set the upper limit for operands based on difficulty.
        val upperLimit = when (difficulty) {
            "easy" -> 10
            "medium" -> 25
            "hard" -> 50
            else -> 10
        }

        // Generate random operands for the math problem.
        var operand1 = Random.nextInt(upperLimit)
        var operand2 = Random.nextInt(upperLimit)

        // Return the problem string and store the correct answer.
        return when (operation) {
            "addition" -> {
                currentAnswer = operand1 + operand2
                "$operand1 + $operand2"
            }
            "subtraction" -> {
                // Ensure operand1 is greater for a positive result.
                if (operand1 < operand2) {
                    val temp = operand1
                    operand1 = operand2
                    operand2 = temp
                }
                currentAnswer = operand1 - operand2
                "$operand1 - $operand2"
            }
            "multiplication" -> {
                currentAnswer = operand1 * operand2
                "$operand1 × $operand2"
            }
            "division" -> {
                // Prevent division by zero and ensure result is an integer.
                while (operand2 == 0) {
                    operand2 = Random.nextInt(upperLimit)
                }
                currentAnswer = operand1 / operand2
                "$operand1 ÷ $operand2"
            }
            else -> {
                currentAnswer = 0
                "Unknown operation"
            }
        }
    }

    private fun setRandomMathProblem() {
        val problem = generateRandomProblem(operation, difficulty)
        mathQuestionTextView.text = problem
    }

    private fun navigateToResults() {
        val currentView = view ?: return
        val action = questionsDirections.actionQuestionsToResults(
            correctAnswers = totalCorrectAnswers,
            incorrectAnswers = totalWrongAnswers,
            questionAmount = numberOfQuestions
        )
        currentView.findNavController().navigate(action)
    }

    private fun checkAnswer() {
        val userInput = etUserAnswer.text.toString().toIntOrNull()

        // Compare user input to the correct answer.
        if (userInput != null) {
            if (userInput == currentAnswer) {
                totalCorrectAnswers++
            } else {
                totalWrongAnswers++
            }

            // Prepare for next question.
            etUserAnswer.text.clear()
            numberOfQuestions--

            // If all questions answered, navigate to results; otherwise, set new problem.
            if (numberOfQuestions <= 0) {
                navigateToResults()
            } else {
                setRandomMathProblem()
            }
        }
    }
}
