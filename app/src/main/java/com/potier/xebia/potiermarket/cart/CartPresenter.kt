package com.potier.xebia.potiermarket.cart

interface CartPresenter {
    fun onDestroy()
    fun calculatePrice()
}