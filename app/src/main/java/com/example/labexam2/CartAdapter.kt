package com.example.labexam2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val cartItems: List<CartItem>) : 
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coffeeImage: ImageView = view.findViewById(R.id.coffeeImage)
        val coffeeName: TextView = view.findViewById(R.id.coffeeName)
        val coffeeSize: TextView = view.findViewById(R.id.coffeeSize)
        val coffeeQuantity: TextView = view.findViewById(R.id.coffeeQuantity)
        val coffeePrice: TextView = view.findViewById(R.id.coffeePrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        holder.coffeeImage.setImageResource(item.coffee.imageResource)
        holder.coffeeName.text = item.coffee.name
        holder.coffeeSize.text = "Size: ${item.size}"
        holder.coffeeQuantity.text = "Quantity: ${item.quantity}"
        holder.coffeePrice.text = "$${String.format("%.2f", item.totalPrice)}"
    }

    override fun getItemCount() = cartItems.size
}
