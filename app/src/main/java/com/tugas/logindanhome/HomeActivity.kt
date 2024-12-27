package com.tugas.logindanhome

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val username = intent.getStringExtra("USERNAME")
        val textViewWelcome = findViewById<TextView>(R.id.textViewWelcome)

        textViewWelcome.text = "Hallo $username"
    }
}
