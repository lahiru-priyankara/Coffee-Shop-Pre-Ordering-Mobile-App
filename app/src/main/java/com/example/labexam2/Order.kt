package com.example.labexam2

data class OrderItem(
    val name: String,
    val quantity: Int,
    val price: Double
)

data class Order(
    val id: String,
    val date: String,
    val status: String,
    val items: List<OrderItem>,
    val total: Double
)
