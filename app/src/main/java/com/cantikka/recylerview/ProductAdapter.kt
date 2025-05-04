package com.cantikka.recylerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Untuk produk pertama
        val imageProduct1: ImageView = itemView.findViewById(R.id.imageProduct)
        val textName1: TextView = itemView.findViewById(R.id.textName)
        val textPrice1: TextView = itemView.findViewById(R.id.textPrice)
        val btnAddToCart1: Button = itemView.findViewById(R.id.btnAddToCart)
        val counterLayout1: LinearLayout = itemView.findViewById(R.id.counterLayout)
        val btnPlus1: ImageButton = itemView.findViewById(R.id.btnPlus)
        val btnMinus1: ImageButton = itemView.findViewById(R.id.btnMinus)
        val textQuantity1: TextView = itemView.findViewById(R.id.textQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.imageProduct1.setImageResource(product.imageResId)
        holder.textName1.text = product.name
        holder.textPrice1.text = product.price
        if (product.quantity == 0) {
            holder.btnAddToCart1.visibility = View.VISIBLE
            holder.counterLayout1.visibility = View.GONE
        } else {
            holder.btnAddToCart1.visibility = View.GONE
            holder.counterLayout1.visibility = View.VISIBLE
            holder.textQuantity1.text = product.quantity.toString()
        }

        holder.btnAddToCart1.setOnClickListener {
            product.quantity = 1
            notifyItemChanged(position)
        }

        holder.btnPlus1.setOnClickListener {
            product.quantity++
            notifyItemChanged(position)
        }

        holder.btnMinus1.setOnClickListener {
            product.quantity--
            if (product.quantity <= 0) {
                product.quantity = 0
            }
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = productList.size
}

