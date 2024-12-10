package com.mitthilesh.richculturalheritagetraditonsofindia

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Collections

class QuizActivity : AppCompatActivity() {
    private val quizQuestions = listOf(
        QuizQuestion(
            "What is the capital of India?",
            listOf("Mumbai", "New Delhi", "Chennai", "Kolkata"),
            "New Delhi"
        ),
        QuizQuestion(
            "Which state is known as the 'Land of Five Rivers'?",
            listOf("Bihar", "Punjab", "Gujarat", "Rajasthan"),
            "Punjab"
        ),
        QuizQuestion(
            "Name the festival of lights in India.",
            listOf("Holi", "Eid", "Diwali", "Christmas"),
            "Diwali"
        ),
        QuizQuestion(
            "Which dance form is classical to Kerala?",
            listOf("Bharatanatyam", "Kathak", "Kathakali", "Odissi"),
            "Kathakali"
        ),
        QuizQuestion(
            "What is the national flower of India?",
            listOf("Rose", "Lotus", "Marigold", "Jasmine"),
            "Lotus"
        ),
        QuizQuestion(
            "Which city is known as the Pink City of India?",
            listOf("Udaipur", "Jodhpur", "Jaipur", "Agra"),
            "Jaipur"
        ),
        QuizQuestion(
            "What is the traditional dress of men in Punjab called?",
            listOf("Sherwani", "Kurta Pajama", "Lungi", "Dhoti"),
            "Kurta Pajama"
        ),
        QuizQuestion(
            "Who composed the Indian national anthem?",
            listOf("Bankim Chandra", "Tagore", "Nehru", "Gandhi"),
            "Tagore"
        ),
        QuizQuestion(
            "Which river is considered holy in Hinduism?",
            listOf("Yamuna", "Ganga", "Godavari", "Kaveri"),
            "Ganga"
        ),
        QuizQuestion(
            "Name the Indian festival that marks the harvest season.",
            listOf("Baisakhi", "Pongal", "Onam", "Makar Sankranti"),
            "Pongal"
        )
    )
        private var currentQuestionIndex = 0
        private var correctAnswers = 0
        private var skippedQuestions = 0

        private lateinit var questionText: TextView
        private lateinit var optionsGroup: RadioGroup
        private lateinit var resultText: TextView
        private lateinit var messageText: TextView
        private lateinit var decorationImage: ImageView
        private lateinit var skipButton: Button
        private lateinit var submitButton: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_quiz)

            questionText = findViewById(R.id.questionText)
            optionsGroup = findViewById(R.id.optionsGroup)
            resultText = findViewById(R.id.resultText)
            messageText = findViewById(R.id.messageText)
            decorationImage = findViewById(R.id.decorationImage)
            skipButton = findViewById(R.id.skipButton)
            submitButton = findViewById(R.id.submitButton)

            val shuffledQuestions = quizQuestions.shuffled()

            showNextQuestion(shuffledQuestions)

            skipButton.setOnClickListener {
                skippedQuestions++
                showNextQuestion(shuffledQuestions)
            }

            submitButton.setOnClickListener {
                val selectedOption = findViewById<RadioButton>(optionsGroup.checkedRadioButtonId)
                if (selectedOption != null && selectedOption.text == shuffledQuestions[currentQuestionIndex - 1].answer) {
                    correctAnswers++
                }
                showNextQuestion(shuffledQuestions)
            }
        }

        private fun showNextQuestion(questions: List<QuizQuestion>) {
            if (currentQuestionIndex < questions.size) {
                val currentQuestion = questions[currentQuestionIndex]
                questionText.text = currentQuestion.question
                optionsGroup.removeAllViews()
                for (option in currentQuestion.options) {
                    val radioButton = RadioButton(this)
                    radioButton.text = option
                    optionsGroup.addView(radioButton)
                }
                currentQuestionIndex++
            } else {
                showResults()
            }
        }

        private fun showResults() {
            val score = correctAnswers
            val totalQuestions = quizQuestions.size
            val message: String
            val decorationResId: Int

            if (score >= 8) {
                message = "Congratulations! 🎉 You scored above 8! Well done!"
                decorationResId = R.drawable.congrats
            } else {
                message = "Try again next time! All the best! 🌟"
                decorationResId = R.drawable.encouragement
            }

            resultText.text = "Correct: $correctAnswers\n" +
                    "Incorrect: ${totalQuestions - correctAnswers - skippedQuestions}\n" +
                    "Skipped: $skippedQuestions"
            messageText.text = message
            decorationImage.setImageResource(decorationResId)

            resultText.visibility = TextView.VISIBLE
            messageText.visibility = TextView.VISIBLE
            decorationImage.visibility = ImageView.VISIBLE
            skipButton.isEnabled = false
            submitButton.isEnabled = false
        }
    }

