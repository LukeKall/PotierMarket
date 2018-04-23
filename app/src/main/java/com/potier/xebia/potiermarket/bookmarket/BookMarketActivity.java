package com.potier.xebia.potiermarket.bookmarket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.potier.xebia.potiermarket.R;
import com.potier.xebia.potiermarket.book.Book;
import com.potier.xebia.potiermarket.bookdetail.BookDetailActivity;
import com.potier.xebia.potiermarket.cart.CartActivity;

import java.util.ArrayList;
import java.util.List;

public class BookMarketActivity extends AppCompatActivity implements BookMarketView, OnBookClickListener {

    private BookMarketPresenter bookMarketPresenter;
    private RecyclerView mRecyclerView;
    private BookMarketAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        mRecyclerView = findViewById(R.id.books_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        bookMarketPresenter.onDestroy();
    }

    private void init() {
        bookMarketPresenter = new BookMarketPresenterImpl(this);
        bookMarketPresenter.getAllBooks();
        Button goCart = findViewById(R.id.go_cart);
        goCart.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartActivity();
            }
        });
    }

    @Override
    public void showBooks(List<Book> bookList) {
        mAdapter = new BookMarketAdapter(bookList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClickAddCartButton(Book book) {
        bookMarketPresenter.addBookInCart(book);
        Toast.makeText(this, "Book added to cart : " + book.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickBook(Book book) {
        openBookDetailActivity(book);
    }

    private void openBookDetailActivity(Book book){
        Intent bookDetailActivity = new Intent(BookMarketActivity.this, BookDetailActivity.class);
        bookDetailActivity.putExtra("book", book);
        startActivity(bookDetailActivity);
    }

    private void openCartActivity(){
        Intent cartActivity = new Intent(BookMarketActivity.this, CartActivity.class);
        cartActivity.putParcelableArrayListExtra("cart", (ArrayList<? extends Parcelable>) bookMarketPresenter.getCart());
        startActivity(cartActivity);
    }
}
