package com.example.rateapp

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioGroup

class ImageRateCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var imageView: ImageView
    private lateinit var radioGroup: RadioGroup

    init {
        LayoutInflater.from(context).inflate(R.layout.card, this, true)

        imageView = findViewById(R.id.imageView)
        radioGroup = findViewById(R.id.radioGroup)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageRateCard, defStyleAttr, 0)
        val imageResId = typedArray.getResourceId(R.styleable.ImageRateCard_imageSrc, 0)

        if (imageResId != 0) {
            imageView.setImageResource(imageResId)
        }
        typedArray.recycle()
    }

    fun setImage(resId: Int) {
        imageView.setImageResource(resId)
    }

    fun getSelectedValue(): Int {
        return when (radioGroup.checkedRadioButtonId) {
            R.id.radioButton1 -> 1
            R.id.radioButton2 -> 2
            R.id.radioButton3 -> 3
            else -> -1
        }
    }
}