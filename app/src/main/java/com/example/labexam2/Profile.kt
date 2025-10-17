package com.example.labexam2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import de.hdodenhof.circleimageview.CircleImageView

class Profile : AppCompatActivity() {
    private lateinit var ivProfileImage: CircleImageView
    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var btnSave: MaterialButton
    private lateinit var btnEdit: MaterialButton
    private lateinit var tilName: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPhone: TextInputLayout
    private lateinit var rvOrders: androidx.recyclerview.widget.RecyclerView
    private lateinit var orderAdapter: OrderAdapter

    // Sample orders data
    private val orders = listOf(
        Order(
            id = "123456",
            date = "Mar 20, 2025",
            status = "Delivered",
            items = listOf(
                OrderItem("Classic Cappuccino", 1, 3.50),
                OrderItem("Vanilla Latte", 1, 4.00)
            ),
            total = 7.50
        ),
        Order(
            id = "123457",
            date = "Mar 18, 2025",
            status = "Delivered",
            items = listOf(
                OrderItem("Espresso", 1, 2.50),
                OrderItem("Caramel Macchiato", 1, 4.50)
            ),
            total = 7.00
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize views
        ivProfileImage = findViewById(R.id.ivProfileImage)
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        btnSave = findViewById(R.id.btnSave)
        btnEdit = findViewById(R.id.btnEdit)
        tilName = findViewById(R.id.tilName)
        tilEmail = findViewById(R.id.tilEmail)
        tilPhone = findViewById(R.id.tilPhone)
        rvOrders = findViewById(R.id.rvOrders)

        // Set default values
        etName.setText("Priya")
        etEmail.setText("Priya@example.com")
        etPhone.setText("+94 752683430")



        // Show both buttons but make them non-functional
        btnSave.visibility = MaterialButton.VISIBLE
        btnEdit.visibility = MaterialButton.VISIBLE

        // Set up RecyclerView
        rvOrders.layoutManager = LinearLayoutManager(this)
        orderAdapter = OrderAdapter()
        rvOrders.adapter = orderAdapter
        orderAdapter.submitList(orders)

        // Set up profile image click listener
        ivProfileImage.setOnClickListener {
            // Do nothing when profile image is clicked
        }

        // Set up edit button click listener
        btnEdit.setOnClickListener {
            // Do nothing when edit button is clicked
        }

        // Set up save button click listener
        btnSave.setOnClickListener {
            // Do nothing when save button is clicked
        }
    }

    // Function to add new order
    fun addNewOrder(order: Order) {
        val updatedOrders = orders.toMutableList().apply {
            add(0, order) // Add new order at the top
        }
        orderAdapter.submitList(updatedOrders)
    }
}
