package com.potier.xebia.potiermarket.cart

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.potier.xebia.potiermarket.R
import com.potier.xebia.potiermarket.book.Book

class CartActivity : AppCompatActivity(), CartView {

    private var mRecyclerView: RecyclerView? = null
    private var cartAdapter: CartAdapter? = null
    private var cartPresenter: CartPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        mRecyclerView = findViewById(R.id.cart_recycler_view)
        mRecyclerView!!.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager

        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        cartPresenter!!.onDestroy()
    }

    fun init() {
        val param = intent.extras
        var cart:List<Book> = ArrayList()
        if (param != null) {
            cart = param.getParcelableArrayList("cart")
        }
        cartPresenter = CartPresenterImpl(this, cart)
        cartPresenter!!.calculatePrice()
        showBooks(cart)

    }

    fun showBooks(cart: List<Book>) {
        cartAdapter = CartAdapter(cart)
        mRecyclerView!!.adapter = cartAdapter
    }

    override fun showPrice(price: Double) {
        val priceView = this.findViewById<TextView>(R.id.total_price)
        priceView.text = price.toString() +  "€"
    }
}
