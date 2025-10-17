package com.example.labexam2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.appcompat.widget.AppCompatImageButton

class MoodRecommendationsActivity : AppCompatActivity() {
    private lateinit var cardEnergetic: CardView
    private lateinit var cardRelaxed: CardView
    private lateinit var cardHappy: CardView
    private lateinit var cardRomantic: CardView
    private lateinit var cardFocused: CardView
    private lateinit var cardTired: CardView
    private lateinit var cardCoffeeRecommendation: CardView
    private lateinit var tvCoffeeTitle: TextView
    private lateinit var ivCoffeeImage: ImageView
    private lateinit var tvCoffeeDescription: TextView
    private lateinit var btnBack: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_recommendations)

        // Initialize views
        cardEnergetic = findViewById(R.id.cardEnergetic)
        cardRelaxed = findViewById(R.id.cardRelaxed)
        cardHappy = findViewById(R.id.cardHappy)
        cardRomantic = findViewById(R.id.cardRomantic)
        cardFocused = findViewById(R.id.cardFocused)
        cardTired = findViewById(R.id.cardTired)
        cardCoffeeRecommendation = findViewById(R.id.cardCoffeeRecommendation)
        tvCoffeeTitle = findViewById(R.id.tvCoffeeTitle)
        ivCoffeeImage = findViewById(R.id.ivCoffeeImage)
        tvCoffeeDescription = findViewById(R.id.tvCoffeeDescription)
        btnBack = findViewById(R.id.btnBack)

        // Set click listeners for mood cards
        cardEnergetic.setOnClickListener { showRecommendation("Energetic", R.drawable.energetic, "Strong Espresso", "A robust espresso to give you that extra boost when you need it most. Perfect for those early mornings or when you need a quick energy lift.", R.drawable.double_espresso) }
        cardRelaxed.setOnClickListener { showRecommendation("Relaxed", R.drawable.relaxed, "Vanila Latte", "A soothing vanila latte blend that helps you unwind and relax. Perfect for those moments when you need to take a break and find your calm.", R.drawable.vanila_latte) }
        cardHappy.setOnClickListener { showRecommendation("Happy", R.drawable.happy, "Caramel Macchiato", "A sweet and creamy caramel macchiato that will lift your spirits. Perfect for those happy moments or when you need a little pick-me-up.", R.drawable.caramel_macchiato) }
        cardRomantic.setOnClickListener { showRecommendation("Romantic", R.drawable.romantic, "Classic Cappuccino", "A warm and comforting classic cappuccino that creates a cozy atmosphere. Perfect for romantic moments or when you want to feel pampered.", R.drawable.classic_cappuchino) }
        cardFocused.setOnClickListener { showRecommendation("Focused", R.drawable.focused, "Mocha Cold Brew", "A refreshing mocha cold brew that enhances concentration and focus. Perfect for those moments when you need to stay sharp and productive.", R.drawable.mocha_choclate_cold_brew) }
        cardTired.setOnClickListener { showRecommendation("Tired", R.drawable.tired, "Americano", "A smooth americano that provides comfort without the caffeine. Perfect for those tired moments when you need a gentle pick-me-up.", R.drawable.americano) }

        // Set click listener for Order Now button
        findViewById<View>(R.id.btnOrderNow).setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }

        // Set click listener for Back button
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun showRecommendation(mood: String, moodIcon: Int, coffeeName: String, description: String, coffeeImage: Int) {
        // Update recommendation card
        tvCoffeeTitle.text = coffeeName
        tvCoffeeDescription.text = description
        ivCoffeeImage.setImageResource(coffeeImage)

        // Show recommendation card
        cardCoffeeRecommendation.visibility = View.VISIBLE

        // Animate the selected mood card
        animateSelectedCard(mood)
    }

    private fun animateSelectedCard(mood: String) {
        val selectedCard = getSelectedCard(mood)
        selectedCard?.let { card ->
            card.animate()
                .scaleX(1.1f)
                .scaleY(1.1f)
                .setDuration(200)
                .withEndAction {
                    card.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(200)
                        .start()
                }
                .start()
        }
    }

    private fun getSelectedCard(mood: String): CardView? {
        return when (mood) {
            "Energetic" -> cardEnergetic
            "Relaxed" -> cardRelaxed
            "Happy" -> cardHappy
            "Romantic" -> cardRomantic
            "Focused" -> cardFocused
            "Tired" -> cardTired
            else -> null
        }
    }
}
