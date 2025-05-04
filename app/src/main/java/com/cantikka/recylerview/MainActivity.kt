package com.cantikka.recylerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 kolom

        val productList = listOf(
            Product("Iphone 16 Pink", "Rp 20.000.000", R.drawable.iphone16pink),
            Product("Iphone 16 Hijau", "Rp 21.000.000", R.drawable.iphone16hijau),
            Product("Iphone 16 Ungu", "Rp 20.500.000", R.drawable.iphone16ungu),
            Product("Iphone 16 Hitam", "Rp 22.000.000", R.drawable.iphone16hitam),
            Product("Iphone 16 Putih", "Rp 21.000.000", R.drawable.iphone16putih),
            Product("Iphone 16Pro Hitam", "Rp 26.000.000", R.drawable.iphone16promaxhitam),
            Product("Iphone 16Pro Putih", "Rp 26.500.000", R.drawable.iphone16promaxputih),
            Product("Iphone 16Pro Titanium", "Rp 27.000.000", R.drawable.iphone16promaxtitanium)
            // tambahkan produk lainnya
        )

        productAdapter = ProductAdapter(productList)
        recyclerView.adapter = productAdapter
    }
}
