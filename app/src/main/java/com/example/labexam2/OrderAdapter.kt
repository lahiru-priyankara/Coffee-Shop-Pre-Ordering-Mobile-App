package com.example.labexam2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter : ListAdapter<Order, OrderAdapter.OrderViewHolder>(OrderDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class OrderViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        private val tvOrderNumber: TextView = view.findViewById(R.id.tvOrderNumber)
        private val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        private val tvDate: TextView = view.findViewById(R.id.tvDate)
        private val llItems: ViewGroup = view.findViewById(R.id.llItems)
        private val tvTotal: TextView = view.findViewById(R.id.tvTotal)

        fun bind(order: Order) {
            tvOrderNumber.text = "Order #${order.id}"
            tvStatus.text = order.status
            tvDate.text = order.date
            
            // Clear existing items
            llItems.removeAllViews()
            
            // Add items
            order.items.forEach { item ->
                val itemLayout = LayoutInflater.from(view.context)
                    .inflate(R.layout.item_order_item, llItems, false)
                
                itemLayout.findViewById<TextView>(R.id.tvItemName).text = item.name
                itemLayout.findViewById<TextView>(R.id.tvItemQuantity).text = "x${item.quantity}"
                itemLayout.findViewById<TextView>(R.id.tvItemPrice).text = "\$${"%.2f".format(item.price)}"
                
                llItems.addView(itemLayout)
            }
            
            tvTotal.text = "Total: \$${"%.2f".format(order.total)}"
        }
    }

    private class OrderDiffCallback : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }
    }
}
