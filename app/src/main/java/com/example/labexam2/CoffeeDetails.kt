package com.example.labexam2

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

private const val DUMMY_SIZE = "M"
private const val DUMMY_QUANTITY = 1
private const val DUMMY_PRICE = 4.99

class CoffeeDetails : AppCompatActivity() {
    private lateinit var coffee: CoffeeItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_details)

        coffee = intent.getSerializableExtra("coffee") as CoffeeItem

        // Initialize views
        val coffeeImage = findViewById<ImageView>(R.id.coffeeImage)
        val coffeeName = findViewById<TextView>(R.id.coffeeName)
        val coffeeDescription = findViewById<TextView>(R.id.coffeeDescription)
        val coffeePrice = findViewById<TextView>(R.id.coffeePrice)
        val quantityEditText = findViewById<EditText>(R.id.quantityEditText)
        val decreaseQuantity = findViewById<ImageButton>(R.id.decreaseQuantity)
        val increaseQuantity = findViewById<ImageButton>(R.id.increaseQuantity)
        val addToCartButton = findViewById<MaterialButton>(R.id.addToCartButton)

        // Set coffee details
        coffeeImage.setImageResource(coffee.imageResource)
        coffeeName.text = coffee.name
        coffeeDescription.text = coffee.description
        coffeePrice.text = "$${DUMMY_PRICE}"
        quantityEditText.setText(DUMMY_QUANTITY.toString())

        // Disable quantity controls
        decreaseQuantity.isEnabled = false
        increaseQuantity.isEnabled = false

        // Disable size selection
        val sizeSmall = findViewById<TextView>(R.id.sizeSmall)
        val sizeMedium = findViewById<TextView>(R.id.sizeMedium)
        val sizeLarge = findViewById<TextView>(R.id.sizeLarge)

        sizeSmall.isEnabled = false
        sizeMedium.isEnabled = false
        sizeLarge.isEnabled = false

        // Add to cart button
        addToCartButton.setOnClickListener {
            // Navigate to home page
            startActivity(Intent(this, Home::class.java))
            finish()
        }
    }
}
