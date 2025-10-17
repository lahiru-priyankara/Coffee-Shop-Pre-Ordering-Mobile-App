package com.example.labexam2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OrderConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        findViewById<Button>(R.id.backToHomeButton).setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            finishAffinity()
        }
    }
}
