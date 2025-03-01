package com.example.rateapp

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView

class ImageRateCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var imageView: ImageView
    private lateinit var radioGroup: RadioGroup
    private lateinit var nameTextView: TextView
    private var onValueSelectedListener: ((Int) -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.card, this, true)

        imageView = findViewById(R.id.imageView)
        radioGroup = findViewById(R.id.radioGroup)
        nameTextView = findViewById(R.id.imageName)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedValue = when (checkedId) {
                R.id.radioButton1 -> 1
                R.id.radioButton2 -> 2
                R.id.radioButton3 -> 3
                else -> -1
            }
            onValueSelectedListener?.invoke(selectedValue)
        }
    }

    fun setImage(resId: Int) {
        imageView.setImageResource(resId)
    }

    fun setName(name: String) {
        nameTextView.text = name
    }

    fun setOnValueSelectedListener(listener: (Int) -> Unit) {
        this.onValueSelectedListener = listener
    }
}