package com.example.project4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class welcome : Fragment() {

    // UI
    private lateinit var btnStart: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonPlus: Button
    private lateinit var editTextNumberOfQuestions: EditText
    private lateinit var radioEasy: RadioButton
    private lateinit var radioMedium: RadioButton
    private lateinit var radioHard: RadioButton
    private lateinit var radioAddition: RadioButton
    private lateinit var radioSubtraction: RadioButton
    private lateinit var radioMultiplication: RadioButton
    private lateinit var radioDivision: RadioButton

    var difficulty = "easy" // Default difficulty
    var operation = "addition" // Default math operation
    var numberOfQuestions = 10 // Default number of questions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize UI components
        btnStart = view.findViewById(R.id.btn_start)
        buttonMinus = view.findViewById(R.id.button_minus)
        buttonPlus = view.findViewById(R.id.button_plus)
        editTextNumberOfQuestions = view.findViewById(R.id.edittext_number_of_questions)
        radioEasy = view.findViewById(R.id.radio_easy)
        radioMedium = view.findViewById(R.id.radio_medium)
        radioHard = view.findViewById(R.id.radio_hard)
        radioAddition = view.findViewById(R.id.radio_addition)
        radioSubtraction = view.findViewById(R.id.radio_subtraction)
        radioMultiplication = view.findViewById(R.id.radio_multiplication)
        radioDivision = view.findViewById(R.id.radio_division)

        // Start quiz button click listener
        btnStart.setOnClickListener {
            navigateToQuestionsFragment()

            // Navigate to questions fragment with user's selections
            val action = welcomeDirections.actionWelcomeToQuestions(
                numberOfQuestions, 0, 0, operation, difficulty
            )
            view.findNavController().navigate(action)
        }

        // Decrease the number of questions by 1
        buttonMinus.setOnClickListener {
            val currentNum = editTextNumberOfQuestions.text.toString().toInt()
            if (currentNum > 1) { // Prevent going below 1
                editTextNumberOfQuestions.setText((currentNum - 1).toString())
            }
        }

        // Increase the number of questions by 1
        buttonPlus.setOnClickListener {
            val currentNum = editTextNumberOfQuestions.text.toString().toInt()
            editTextNumberOfQuestions.setText((currentNum + 1).toString())
        }
    }

    private fun navigateToQuestionsFragment() {
        // Get difficulty level
        difficulty = when {
            radioEasy.isChecked -> "easy"
            radioMedium.isChecked -> "medium"
            radioHard.isChecked -> "hard"
            else -> "easy"
        }

        // Get operation type
        operation = when {
            radioAddition.isChecked -> "addition"
            radioSubtraction.isChecked -> "subtraction"
            radioMultiplication.isChecked -> "multiplication"
            radioDivision.isChecked -> "division"
            else -> "addition"
        }

        // Get number of questions
        numberOfQuestions = editTextNumberOfQuestions.text.toString().toInt()
    }
}
