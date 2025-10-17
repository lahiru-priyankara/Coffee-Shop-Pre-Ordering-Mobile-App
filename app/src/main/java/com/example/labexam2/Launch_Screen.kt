package com.example.labexam2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class Launch_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, Onboarding_Screen_1::class.java))
            finish()
        }, 3000)
    }
}