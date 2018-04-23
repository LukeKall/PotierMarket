package com.potier.xebia.potiermarket.book

import com.google.gson.annotations.SerializedName

enum class OfferType(val type: String) {

    @SerializedName("percentage")
    PERCENTAGE("PERCENTAGE") {
        override fun calculatePrice(value: Double, price: Double, sliceValue: Int?): Double {
            return price - value * price / 100
        }
    },
    @SerializedName("minus")
    MINUS("MINUS") {
        override fun calculatePrice(value: Double, price: Double, sliceValue: Int?): Double {
            return price - value
        }
    },
    @SerializedName("slice")
    SLICE("SLICE") {
        override fun calculatePrice(value: Double, price: Double, sliceValue: Int?): Double {
            return price - ((price/sliceValue!!).toInt() * value)
        }
    };

    abstract fun calculatePrice(value: Double, price: Double, sliceValue: Int? = null): Double

}