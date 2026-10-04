package com.example.qrscanner2

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        val copyButton = findViewById<Button>(R.id.btnCopy)
        val resultText = findViewById<TextView>(R.id.resultText)

        copyButton.setOnClickListener {
            val clipboard =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            val clip = ClipData.newPlainText(
                "QR Result",
                resultText.text.toString()
            )

            clipboard.setPrimaryClip(clip)

            Toast.makeText(
                this,
                "Copied to clipboard",
                Toast.LENGTH_SHORT
            ).show()
        }

        val shareButton = findViewById<Button>(R.id.btnShare)

        shareButton.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)

            intent.type = "text/plain"

            intent.putExtra(
                Intent.EXTRA_TEXT,
                resultText.text.toString()
            )

            startActivity(
                Intent.createChooser(intent, "Share QR result")
            )
        }
    }
}