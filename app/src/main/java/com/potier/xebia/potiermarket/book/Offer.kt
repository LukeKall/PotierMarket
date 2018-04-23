package com.potier.xebia.potiermarket.book


class Offer @JvmOverloads constructor(private val type: OfferType,
                                      private val value: Double,
                                      private val sliceValue: Int?) {

    fun calculatePrice(price: Double): Double {
        return type.calculatePrice(value, price, sliceValue)
    }
}
