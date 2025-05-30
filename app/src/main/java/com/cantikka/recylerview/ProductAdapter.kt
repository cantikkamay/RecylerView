package com.cantikka.recylerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.cantikka.recyclerview.RecyclerItem

class ProductAdapter(private val items: List<RecyclerItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_PRODUCT = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is RecyclerItem.Header -> TYPE_HEADER
            is RecyclerItem.ProductItem -> TYPE_PRODUCT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product, parent, false)
            ProductViewHolder(view)
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is RecyclerItem.Header -> (holder as HeaderViewHolder).bind(item)
            is RecyclerItem.ProductItem -> (holder as ProductViewHolder).bind(item.product)
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(header: RecyclerItem.Header) {
            val textHeader = itemView.findViewById<TextView>(R.id.textHeader)
            textHeader.text = header.title
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageProduct = itemView.findViewById<ImageView>(R.id.imageProduct)
        private val textName = itemView.findViewById<TextView>(R.id.textName)
        private val textPrice = itemView.findViewById<TextView>(R.id.textPrice)
        private val btnAddToCart = itemView.findViewById<Button>(R.id.btnAddToCart)
        private val counterLayout = itemView.findViewById<LinearLayout>(R.id.counterLayout)
        private val btnPlus = itemView.findViewById<ImageButton>(R.id.btnPlus)
        private val btnMinus = itemView.findViewById<ImageButton>(R.id.btnMinus)
        private val textQuantity = itemView.findViewById<TextView>(R.id.textQuantity)

        fun bind(product: Product) {
            imageProduct.setImageResource(product.imageResId)
            textName.text = product.name
            textPrice.text = product.price

            if (product.quantity == 0) {
                btnAddToCart.visibility = View.VISIBLE
                counterLayout.visibility = View.GONE
            } else {
                btnAddToCart.visibility = View.GONE
                counterLayout.visibility = View.VISIBLE
                textQuantity.text = product.quantity.toString()
            }

            btnAddToCart.setOnClickListener {
                product.quantity = 1
                notifyChange()
            }

            btnPlus.setOnClickListener {
                product.quantity++
                notifyChange()
            }

            btnMinus.setOnClickListener {
                product.quantity--
                if (product.quantity < 1) product.quantity = 0
                notifyChange()
            }
        }

        private fun notifyChange() {
            (itemView.parent as? RecyclerView)?.let {
                it.adapter?.notifyItemChanged(adapterPosition)
            }
        }
    }
}
