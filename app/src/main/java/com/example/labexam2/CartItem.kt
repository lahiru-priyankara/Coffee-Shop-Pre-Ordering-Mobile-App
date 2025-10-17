package com.example.labexam2

data class CartItem(
    val coffee: CoffeeItem,
    val size: String,
    var quantity: Int,
    var totalPrice: Double
) : java.io.Serializable {
    fun updatePrice() {
        val sizeMultiplier = when (size) {
            "S" -> 1.0
            "M" -> 1.2
            "L" -> 1.4
            else -> 1.0
        }
        totalPrice = coffee.price * sizeMultiplier * quantity
    }
}
