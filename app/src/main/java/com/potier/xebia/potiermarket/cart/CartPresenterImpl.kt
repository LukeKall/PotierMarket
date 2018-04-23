package com.potier.xebia.potiermarket.cart

import com.potier.xebia.potiermarket.book.Book
import com.potier.xebia.potiermarket.book.BookApi
import com.potier.xebia.potiermarket.book.Offer
import com.potier.xebia.potiermarket.book.OfferResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.stream.Collectors

class CartPresenterImpl @JvmOverloads constructor(private var cartView: CartView,
                                                  private var cart: List<Book>) : CartPresenter {

    private var callOffers: Call<OfferResponse>? = null

    override fun onDestroy() {
        callOffers!!.cancel()
    }

    override fun calculatePrice() {
        val bookService = BookApi.bookApi

        callOffers = bookService.listOffers(cart.stream()
                .map(Book::getIsbn)
                .collect(Collectors.toList())
                .joinToString())

        callOffers!!.enqueue(object : Callback<OfferResponse> {

            override fun onResponse(call: Call<OfferResponse>?, response: Response<OfferResponse>?) {
                calculatePrice(response!!.body().offers)
            }

            override fun onFailure(call: Call<OfferResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun calculatePrice(offers: List<Offer>) {
        val actualPrice = cart.stream()
                .mapToDouble { book -> book.price }
                .sum()
        val bestPrice = offers.stream()
                .mapToDouble { offer -> offer.calculatePrice(actualPrice) }
                .min()

        cartView.showPrice(bestPrice.orElse(0.0))
    }

}

