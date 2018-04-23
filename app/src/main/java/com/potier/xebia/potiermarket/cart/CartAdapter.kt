package com.potier.xebia.potiermarket.cart

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.potier.xebia.potiermarket.R
import com.potier.xebia.potiermarket.book.Book
import com.squareup.picasso.Picasso
import java.io.IOException


class CartAdapter(val cart: List<Book>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val bookTitleView: TextView = v.findViewById(R.id.book_title)
        private val bookPriceView: TextView = v.findViewById(R.id.book_price)
        private val imageView: ImageView = v.findViewById(R.id.book_image)

        @Throws(IOException::class)
        fun setBindBook(book: Book) {
            bookTitleView.text = book.title
            bookPriceView.text = book.price.toString() + "€"
            Picasso.get().load(book.cover).into(imageView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.cart_list, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        try {
            viewHolder.setBindBook(cart[position])
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun getItemCount(): Int {
        return cart.size
    }
}