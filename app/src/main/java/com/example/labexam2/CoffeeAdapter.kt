package com.example.labexam2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoffeeAdapter(
    private var coffeeItems: List<CoffeeItem>,
    private val onFavoriteClick: (CoffeeItem) -> Unit
) : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {

    class CoffeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coffeeImage: ImageView = view.findViewById(R.id.coffeeImage)
        val coffeeName: TextView = view.findViewById(R.id.coffeeName)
        val coffeeDescription: TextView = view.findViewById(R.id.coffeeDescription)
        val coffeePrice: TextView = view.findViewById(R.id.coffeePrice)
        val favoriteButton: ImageButton = view.findViewById(R.id.favoriteButton)
        val addButton: ImageButton = view.findViewById(R.id.addButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.coffee_item, parent, false)
        return CoffeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        val coffee = coffeeItems[position]
        holder.coffeeImage.setImageResource(coffee.imageResource)
        holder.coffeeName.text = coffee.name
        holder.coffeeDescription.text = coffee.description
        holder.coffeePrice.text = "$${coffee.price}"
        
        // Update favorite button image based on status
        holder.favoriteButton.setImageResource(
            if (coffee.isFavorite) R.drawable.ic_favorite_filled
            else R.drawable.ic_favorite_border
        )

        // Set click listeners
        holder.favoriteButton.setOnClickListener {
            onFavoriteClick(coffee)
        }

        holder.addButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, CoffeeDetails::class.java)
            intent.putExtra("coffee", coffee)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = coffeeItems.size

    fun updateItems(newItems: List<CoffeeItem>) {
        coffeeItems = newItems
        notifyDataSetChanged()
    }
}
