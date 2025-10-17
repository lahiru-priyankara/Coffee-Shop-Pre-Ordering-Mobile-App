package com.example.labexam2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

object FavoritesManager {
    // Fixed set of favorite items that cannot be modified
    private val favorites = setOf(
        CoffeeItem("Classic Espresso", "Rich and bold", 2.50, 4.5f, R.drawable.classic_espresso, true),
        CoffeeItem("Classic Cappuccino", "Perfect balance", 3.50, 4.7f, R.drawable.classic_cappuchino, true),
        CoffeeItem("Dark Mocha", "Rich Chocolate", 4.00, 4.6f, R.drawable.dark_mocha, true),
        CoffeeItem("Vanilla Latte", "Sweet Aroma", 4.00, 4.5f, R.drawable.vanila_latte, true)
    )

    fun isFavorite(coffee: CoffeeItem): Boolean {
        return favorites.any { it.name == coffee.name }
    }

    fun getFavorites(): List<CoffeeItem> {
        return favorites.toList()
    }
}

class Home : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var rvCoffeeList: RecyclerView
    private lateinit var coffeeAdapter: CoffeeAdapter
    private lateinit var bottomNavigation: BottomNavigationView

    private val categories = listOf(
        "Cappuccino", "Espresso", "Latte", "Mocha",
        "Flat White", "Cold Brew", "Macchiato"
    )

    private val coffeeItems = mapOf(
        "Espresso" to listOf(
            CoffeeItem("Classic Espresso", "Rich and bold", 2.50, 4.5f, R.drawable.classic_espresso),
            CoffeeItem("Double Espresso", "Extra strong", 3.00, 4.6f, R.drawable.double_espresso),
            CoffeeItem("Ristretto", "Concentrated", 2.75, 4.4f, R.drawable.ristretto),
            CoffeeItem("Lungo", "Long pulled", 2.75, 4.3f, R.drawable.lungo),
            CoffeeItem("Americano", "Diluted", 3.00, 4.5f, R.drawable.americano)
        ),
        "Cappuccino" to listOf(
            CoffeeItem("Classic Cappuccino", "Perfect balance", 3.50, 4.7f, R.drawable.classic_cappuchino),
            CoffeeItem("Vanilla Cappuccino", "Sweet vanilla", 4.00, 4.5f, R.drawable.vanila_cappuchino),
            CoffeeItem("Caramel Cappuccino", "Rich caramel", 4.00, 4.6f, R.drawable.caramal_cappuchino),
            CoffeeItem("Almond Cappuccino", "Nutty flavor", 4.25, 4.4f, R.drawable.almond_cappuchino),
            CoffeeItem("Mocha Cappuccino", "Chocolate blend", 4.25, 4.8f, R.drawable.mocha_cappuchino)
        ),
        "Latte" to listOf(
            CoffeeItem("Classic Latte", "Smooth Espresso", 3.50, 4.7f, R.drawable.classic_latte),
            CoffeeItem("Vanilla Latte", "Sweet Aroma", 4.00, 4.5f, R.drawable.vanila_latte),
            CoffeeItem("Caramel Latte", "Buttery Smooth", 4.00, 4.6f, R.drawable.caramal_latte),
        ),
        "Mocha" to listOf(
            CoffeeItem("Classic Mocha", "Chocolate Bliss", 3.50, 4.7f, R.drawable.classic_mocha),
            CoffeeItem("White Mocha", "Creamy Cocoa", 4.00, 4.5f, R.drawable.white_mocha),
            CoffeeItem("Dark Mocha", "Rich Chocolate", 4.00, 4.6f, R.drawable.dark_mocha),
        ),
        "Flat White" to listOf(
            CoffeeItem("Classic Flat White ", "Velvety Espresso", 3.50, 4.7f, R.drawable.classic_fat_white),
            CoffeeItem("Honey Flat White", "Sweet Silk", 4.00, 4.5f, R.drawable.honney_flat_white),
        ),
        "Cold Brew" to listOf(
            CoffeeItem("Classic Cold Brew", "Bold Smooth", 3.50, 4.7f, R.drawable.classic_cold_brew),
            CoffeeItem("Vanilla Cold Brew ", "Sweet Chill", 4.00, 4.5f, R.drawable.vanilla_cold_brew),
            CoffeeItem("Mocha Cold Brew", "Chilled Cocoa", 4.00, 4.6f, R.drawable.mocha_choclate_cold_brew),
        ),
        "Macchiato" to listOf(
            CoffeeItem("Espresso Macchiato", "Bold Spot", 3.50, 4.7f, R.drawable.espresso_macchiato),
            CoffeeItem("Caramel Macchiato ", "Sweet Layers", 4.00, 4.5f, R.drawable.caramel_macchiato),
            CoffeeItem("Hazelnut Macchiato", "Nutty Rich", 4.00, 4.6f, R.drawable.hazelnut_espresso),
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize views
        tabLayout = findViewById(R.id.tabLayout)
        rvCoffeeList = findViewById(R.id.rvCoffeeList)
        bottomNavigation = findViewById(R.id.bottomNavigation)
        val ivProfile = findViewById<ImageView>(R.id.ivProfile)

        // Set up profile image click listener
        ivProfile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        // Set up RecyclerView
        rvCoffeeList.layoutManager = GridLayoutManager(this, 2)
        setupTabs()
        loadCoffeeItems("Cappuccino")

        // Set up bottom navigation
        setupBottomNavigation()
    }

    private fun setupTabs() {
        categories.forEach { category ->
            tabLayout.addTab(tabLayout.newTab().setText(category))
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.text?.toString()?.let { category ->
                    loadCoffeeItems(category)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun loadCoffeeItems(category: String) {
        val items = coffeeItems[category] ?: emptyList()
        
        // Update isFavorite status for each item
        val updatedItems = items.map { coffee ->
            coffee.copy(isFavorite = FavoritesManager.isFavorite(coffee))
        }
        
        // Create adapter with no click handler since we don't want to allow adding to favorites
        coffeeAdapter = CoffeeAdapter(updatedItems) { coffee ->
            // Do nothing when favorite icon is clicked
        }
        rvCoffeeList.adapter = coffeeAdapter
    }

    private fun setupBottomNavigation() {
        findViewById<BottomNavigationView>(R.id.bottomNavigation).apply {
            selectedItemId = R.id.nav_home
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_home -> true
                    R.id.nav_mood -> {
                        startActivity(Intent(this@Home, MoodRecommendationsActivity::class.java))
                        true
                    }
                    R.id.nav_favorites -> {
                        startActivity(Intent(this@Home, Favorites::class.java))
                        true
                    }
                    R.id.nav_cart -> {
                        startActivity(Intent(this@Home, Cart::class.java))
                        true
                    }
                    else -> false
                }
            }
        }
    }
}

data class CoffeeItem(
    val name: String,
    val description: String,
    val price: Double,
    val rating: Float,
    val imageResource: Int,
    var isFavorite: Boolean = false
) : java.io.Serializable