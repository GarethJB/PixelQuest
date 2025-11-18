package com.jb.pixelquest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val message = TextView(this).apply {
            text = getString(R.string.app_name)
            textSize = 20f
            setPadding(32, 32, 32, 32)
        }

        setContentView(message)
    }
}


