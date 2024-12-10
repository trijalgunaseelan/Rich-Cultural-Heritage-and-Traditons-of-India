package com.mitthilesh.richculturalheritagetraditonsofindia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quizButton: ImageButton = findViewById(R.id.quizButton)
        quizButton.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }
        val button2: ImageButton = findViewById(R.id.button2)
        val button3: ImageButton = findViewById(R.id.button3)
        }
    }
