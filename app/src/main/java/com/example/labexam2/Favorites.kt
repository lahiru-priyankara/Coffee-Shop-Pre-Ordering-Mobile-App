package com.example.labexam2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Favorites : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        setupRecyclerView()
        setupBottomNavigation()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvFavorites)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = CoffeeAdapter(FavoritesManager.getFavorites()) { coffee ->
            // Do nothing when favorite icon is clicked
        }
    }

    private fun setupBottomNavigation() {
        findViewById<BottomNavigationView>(R.id.bottomNavigation).apply {
            selectedItemId = R.id.nav_favorites
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_favorites -> true
                    R.id.nav_mood -> {
                        startActivity(Intent(this@Favorites, MoodRecommendationsActivity::class.java))
                        true
                    }
                    R.id.nav_home -> {
                        startActivity(Intent(this@Favorites, Home::class.java))
                        true
                    }

                    R.id.nav_cart -> {
                        startActivity(Intent(this@Favorites, Cart::class.java))
                        true
                    }
                    else -> false
                }
            }
        }
    }
}
