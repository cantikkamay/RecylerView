package com.cantikka.recylerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cantikka.recyclerview.RecyclerItem

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val items = listOf(
            RecyclerItem.Header("iPhone 16 Series"),
            RecyclerItem.ProductItem(Product("iPhone 16 Pink", "Rp 20.000.000", R.drawable.iphone16pink)),
            RecyclerItem.ProductItem(Product("iPhone 16 Putih", "Rp 20.500.000", R.drawable.iphone16putih)),
            RecyclerItem.ProductItem(Product("iPhone 16 Ungu", "Rp 20.000.000", R.drawable.iphone16ungu)),
            RecyclerItem.ProductItem(Product("iPhone 16 Hijau", "Rp 20.500.000", R.drawable.iphone16hijau)),
            RecyclerItem.ProductItem(Product("iPhone 16 Hitam", "Rp 20.000.000", R.drawable.iphone16hitam)),
            RecyclerItem.Header("iPhone 16 Pro Series"),
            RecyclerItem.ProductItem(Product("iPhone 16 ProMax Hitam", "Rp 28.000.000", R.drawable.iphone16promaxhitam)),
            RecyclerItem.ProductItem(Product("iPhone 16 ProMax Titanium", "Rp 29.000.000", R.drawable.iphone16promaxtitanium)),
            RecyclerItem.ProductItem(Product("iPhone 16 ProMax Putih", "Rp 29.000.000", R.drawable.iphone16promaxputih)),
        )

        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (items[position]) {
                    is RecyclerItem.Header -> 2
                    is RecyclerItem.ProductItem -> 1
                }
            }
        }

        recyclerView.layoutManager = layoutManager
        adapter = ProductAdapter(items)
        recyclerView.adapter = adapter
    }
}
