package com.potier.xebia.potiermarket.bookdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.potier.xebia.potiermarket.R
import com.potier.xebia.potiermarket.book.Book
import com.squareup.picasso.Picasso
import java.util.stream.Collectors

class BookDetailActivity : AppCompatActivity() {

    private var book: Book = Book()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        init()
    }

    fun init() {
        getBook()
        setImage()
        setTitle()
        setDescription()
        setIsbn()
        setPrice()
    }

    private fun setImage() {
        val imageView = this.findViewById<ImageView>(R.id.book_detail_image)
        Picasso.get().load(book.cover).into(imageView)
    }

    private fun setTitle(){
        val titleView = this.findViewById<TextView>(R.id.book_detail_title)
        titleView.text = book.title
    }

    private fun setDescription(){
        val descriptionView = this.findViewById<TextView>(R.id.book_detail_synopsis)
        descriptionView.text = book.synopsis.stream().collect(Collectors.joining("\n\n"))
    }

    private fun setIsbn(){
        val isbnView = this.findViewById<TextView>(R.id.book_detail_isbn)
        isbnView.text = book.isbn
    }

    private fun setPrice(){
        val priceView = this.findViewById<TextView>(R.id.book_detail_price)
        priceView.text = book.price.toString() + "€"
    }

    fun getBook() {
        val param = intent.extras
        if (param != null) {
            book = param.getParcelable("book")
        }
    }
}
