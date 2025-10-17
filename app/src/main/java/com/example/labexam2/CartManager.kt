package com.example.labexam2

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    init {
        // Add some dummy data to the cart with realistic prices
        val classicEspresso = CoffeeItem(
            "Classic Espresso",
            "Rich and bold",
            3.99,
            4.5f,
            R.drawable.classic_espresso
        )
        
        val vanillaLatte = CoffeeItem(
            "Vanilla Latte",
            "Sweet Aroma",
            5.99,
            4.5f,
            R.drawable.vanila_latte
        )

        val darkMocha = CoffeeItem(
            "Dark Mocha",
            "Rich Chocolate",
            4.99,
            4.6f,
            R.drawable.dark_mocha
        )

        // Add items with different sizes and quantities
        addItem(CartItem(classicEspresso, "M", 2, 0.0))
        addItem(CartItem(vanillaLatte, "L", 1, 0.0))
        addItem(CartItem(darkMocha, "S", 3, 0.0))
    }

    fun addItem(item: CartItem) {
        val existingItem = cartItems.find { 
            it.coffee.name == item.coffee.name && it.size == item.size 
        }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
            existingItem.totalPrice = existingItem.coffee.price * existingItem.quantity
        } else {
            cartItems.add(item)
        }
    }

    fun getItems(): List<CartItem> = cartItems.toList()

    fun getTotalPrice(): Double = cartItems.sumOf { it.totalPrice }

    fun clearCart() {
        cartItems.clear()
    }

    fun removeItem(item: CartItem) {
        cartItems.remove(item)
    }

    fun updateItemQuantity(item: CartItem, quantity: Int) {
        if (quantity <= 0) {
            removeItem(item)
        } else {
            item.quantity = quantity
            item.totalPrice = item.coffee.price * quantity
        }
    }
}