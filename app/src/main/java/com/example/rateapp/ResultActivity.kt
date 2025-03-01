package com.example.rateapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val selectedImageResName = intent.getIntExtra("selected_image", -1)
        val image: ImageView = findViewById(R.id.resultPic)

        image.setImageResource(selectedImageResName)

        val button: Button = findViewById(R.id.mainPageButton)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}