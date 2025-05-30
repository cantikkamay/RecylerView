package com.cantikka.recyclerview

import com.cantikka.recylerview.Product

sealed class RecyclerItem {
    data class Header(val title: String) : RecyclerItem()
    data class ProductItem(val product: Product) : RecyclerItem()
}
