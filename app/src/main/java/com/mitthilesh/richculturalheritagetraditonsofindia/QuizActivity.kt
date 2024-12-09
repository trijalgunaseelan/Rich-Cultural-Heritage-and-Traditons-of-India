package com.mitthilesh.richculturalheritagetraditonsofindia

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class QuizActivity : AppCompatActivity() {
    private val questions = arrayOf(
        "What is the capital of India?",
        "Which state is known as the 'Land of Five Rivers'?",
        "Name the festival of lights in India.",
        "Which dance form is classical to Kerala?",
        "What is the national flower of India?",
        "Which city is known as the Pink City of India?",
        "What is the traditional dress of men in Punjab called?",
        "Who composed the Indian national anthem?",
        "Which river is considered holy in Hinduism?",
        "Name the Indian festival that marks the harvest season."
    )
    private val options = arrayOf(
        arrayOf("Mumbai", "New Delhi", "Chennai", "Kolkata"),
        arrayOf("Bihar", "Punjab", "Gujarat", "Rajasthan"),
        arrayOf("Holi", "Eid", "Diwali", "Christmas"),
        arrayOf("Bharatanatyam", "Kathak", "Kathakali", "Odissi"),
        arrayOf("Rose", "Lotus", "Marigold", "Jasmine"),
        arrayOf("Udaipur", "Jodhpur", "Jaipur", "Agra"),
        arrayOf("Sherwani", "Kurta Pajama", "Lungi", "Dhoti"),
        arrayOf("Bankim Chandra", "Tagore", "Nehru", "Gandhi"),
        arrayOf("Yamuna", "Ganga", "Godavari", "Kaveri"),
        arrayOf("Baisakhi", "Pongal", "Onam", "Makar Sankranti")
    )
    private val answers = arrayOf(
        "New Delhi", "Punjab", "Diwali", "Kathakali", "Lotus", "Jaipur",
        "Kurta Pajama", "Tagore", "Ganga", "Pongal"
    )

    private var currentQuestion = 0
    private var correctAnswers = 0
    private var skippedQuestions = 0

    private lateinit var questionText: TextView
    private lateinit var optionsGroup: RadioGroup
    private lateinit var resultText: TextView
    private lateinit var skipButton: Button
    private lateinit var submitButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.questionText)
        optionsGroup = findViewById(R.id.optionsGrou)
        resultText = findViewById(R.id.resultText)
        skipButton = findViewById(R.id.skipButton)
        submitButton = findViewById(R.id.submitButton)

        // Randomize questions
        randomizeQuestions()

        // Display the first question
        showNextQuestion()

        skipButton.setOnClickListener {
            skippedQuestions++
            showNextQuestion()
        }

        submitButton.setOnClickListener {
            val selectedOption = findViewById<RadioButton>(optionsGroup.checkedRadioButtonId)
            if (selectedOption != null && selectedOption.text == answers[currentQuestion - 1]) {
                correctAnswers++
            }
            showNextQuestion()
        }
    }

    private fun randomizeQuestions() {
        val indices = (questions.indices).toList().toMutableList()
        Collections.shuffle(indices)
        questions.shuffle()
        options.shuffle()
        answers.shuffle()
    }

    private fun showNextQuestion() {
        if (currentQuestion < questions.size) {
            questionText.text = questions[currentQuestion]
            optionsGroup.removeAllViews()
            for (option in options[currentQuestion]) {
                val radioButton = RadioButton(this)
                radioButton.text = option
                optionsGroup.addView(radioButton)
            }
            currentQuestion++
        } else {
            showResults()
        }
    }

    private fun showResults() {
        resultText.text = "Correct: $correctAnswers\n" +
                "Incorrect: ${questions.size - correctAnswers - skippedQuestions}\n" +
                "Skipped: $skippedQuestions"
        resultText.visibility = TextView.VISIBLE
        skipButton.isEnabled = false
        submitButton.isEnabled = false
    }
}
