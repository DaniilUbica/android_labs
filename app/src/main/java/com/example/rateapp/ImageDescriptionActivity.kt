package com.example.rateapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

class ImageDescriptionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_image_description)

        val imageId = intent.getIntExtra("image_id", -1)
        val imageDescription = intent.getStringExtra("description")
        val image: ImageView = findViewById(R.id.picToWatch)
        val description: TextView = findViewById(R.id.picDescription)

        image.setImageResource(imageId)
        description.text = imageDescription

        val mainPageButton: Button = findViewById(R.id.mainPageButton)
        val shareButton: Button = findViewById(R.id.shareButton)

        mainPageButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        shareButton.setOnClickListener {
            shareImage(imageId)
        }
    }

    private fun shareImage(imageId: Int) {
        val imageUri = getImageUri(imageId)

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, imageUri)
            type = "image/*"
        }

        startActivity(Intent.createChooser(shareIntent, "Share Image via"))
    }

    private fun getImageUri(imageId: Int): Uri {
        val drawable = resources.getDrawable(imageId, theme)
        val bitmap = (drawable as android.graphics.drawable.BitmapDrawable).bitmap

        val cachePath = File(externalCacheDir, "images")
        cachePath.mkdirs()
        val file = File(cachePath, "shared_image.png")
        val outputStream = FileOutputStream(file)
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.close()

        return FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.fileprovider",
            file
        )
    }
}