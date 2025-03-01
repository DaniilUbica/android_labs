package com.example.rateapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Integer.max

class MainActivity : ComponentActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val items = listOf(
            ImageRateCardData(R.drawable.pic_1, -1, "Бзыря", "Просто Бзыря - маленький пес"),
            ImageRateCardData(R.drawable.pic_2, -1, "Свинокот", "То ли кот, то ли свинья, каждый видит в нем что-то свое"),
            ImageRateCardData(R.drawable.pic_3, -1, "Огонек", "Кот с депрессией просит закурить")
        )

        val adapter = ImageRateCardAdapter(items) { position, selectedValue ->
            items[position].selectedValue = selectedValue
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val button: Button = findViewById(R.id.submitButton)
        button.setOnClickListener {
            val selectedValues = items.map { it.selectedValue }
            val maxRate = selectedValues.maxOrNull()

            if (maxRate != null && maxRate != -1) {
                val selectedImageResName = items[selectedValues.indexOf(maxRate)].imageResId
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("selected_image", selectedImageResName)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Rate pictures", Toast.LENGTH_SHORT).show()
            }
        }
    }
}