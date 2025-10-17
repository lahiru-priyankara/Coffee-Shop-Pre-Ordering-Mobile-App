package com.example.labexam2

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText

class Cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        setupRecyclerView()
        setupBottomNavigation()
        setupPickupTime()
        updateTotalPrice()
        setupProceedToPayButton()
    }

    private fun setupPickupTime() {
        findViewById<TextInputEditText>(R.id.pickupTime).addTextChangedListener(object : TextWatcher {
            private var isFormatting = false
            private val divider = ":"

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isFormatting || s == null) return
                isFormatting = true

                val str = s.toString().replace(divider, "")
                if (str.length >= 2) {
                    val hours = str.substring(0, 2)
                    val minutes = if (str.length > 2) str.substring(2) else ""
                    s.replace(0, s.length, "$hours$divider$minutes")
                }

                isFormatting = false
            }
        })
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.cartRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CartAdapter(CartManager.getItems())
    }

    private fun setupBottomNavigation() {
        findViewById<BottomNavigationView>(R.id.bottomNavigation).apply {
            selectedItemId = R.id.nav_cart
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_cart -> true
                    R.id.nav_mood -> {
                        startActivity(Intent(this@Cart, MoodRecommendationsActivity::class.java))
                        true
                    }
                    R.id.nav_favorites -> {
                        startActivity(Intent(this@Cart, Favorites::class.java))
                        true
                    }
                    R.id.nav_home -> {
                        startActivity(Intent(this@Cart, Home::class.java))
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun updateTotalPrice() {
        findViewById<TextView>(R.id.totalPrice).text = "$${String.format("%.2f", CartManager.getTotalPrice())}"
    }

    private fun setupProceedToPayButton() {
        findViewById<Button>(R.id.proceedToPayButton).setOnClickListener {
            val intent = Intent(this, Payment::class.java)
            intent.putExtra("totalAmount", CartManager.getTotalPrice())
            startActivity(intent)
        }
    }
}
