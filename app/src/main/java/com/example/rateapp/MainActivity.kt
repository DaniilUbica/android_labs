package com.example.rateapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import java.lang.Integer.max

class MainActivity : ComponentActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.submitButton)
        button.setOnClickListener {
            val maxRate = getRates().maxByOrNull { it.first }

            if (maxRate != null && maxRate.first != -1) {
                val selectedImageResName = maxRate.second
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("selected_image", selectedImageResName)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Rate pictures", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getRates(): List<Pair<Int, Int>> {
        val firstRate = Pair(findViewById<ImageRateCard>(R.id.card1).getSelectedValue(), R.drawable.pic_1)
        val secondRate = Pair(findViewById<ImageRateCard>(R.id.card2).getSelectedValue(), R.drawable.pic_2)
        val thirdRate = Pair(findViewById<ImageRateCard>(R.id.card3).getSelectedValue(), R.drawable.pic_3)

        return listOf(firstRate, secondRate, thirdRate)
    }
}