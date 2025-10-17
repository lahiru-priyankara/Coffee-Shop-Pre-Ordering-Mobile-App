package com.example.labexam2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val totalAmount = intent.getDoubleExtra("totalAmount", 0.0)
        findViewById<TextView>(R.id.totalAmount).text = "Total Amount: $${String.format("%.2f", totalAmount)}"

        setupPayButton()
    }

    private fun setupPayButton() {
        findViewById<Button>(R.id.payButton).setOnClickListener {
            showConfirmation()
        }
    }

    private fun showConfirmation() {
        CartManager.clearCart()
        startActivity(Intent(this, OrderConfirmation::class.java))
        finishAffinity()
    }
}
