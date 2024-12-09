package com.mitthilesh.richculturalheritagetraditonsofindia

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val quizButton: ImageButton = findViewById(R.id.quizButton)
        quizButton.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        // Placeholder for other buttons
        val button2: ImageButton = findViewById(R.id.button2)
        val button3: ImageButton = findViewById(R.id.button3)

        // Set up your menu icon click listener here
        val menuIcon: ImageButton = findViewById(R.id.menuIcon)
        menuIcon.setOnClickListener {
            // Open your menu or drawer
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_quiz -> {
                startActivity(Intent(this, QuizActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
