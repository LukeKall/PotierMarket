package com.potier.xebia.potiermarket.bookmarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import com.potier.xebia.potiermarket.R;
import com.potier.xebia.potiermarket.book.Book;

import java.util.List;

public class BookMarketActivity extends AppCompatActivity implements BookMarketView, OnBookClickListener {

    protected BookMarketPresenter bookMarketPresenter;
    protected RecyclerView mRecyclerView;
    protected BookMarketAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.books_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }

    private void init() {
        bookMarketPresenter = new BookMarketPresenterImpl(this);
        bookMarketPresenter.getAllBooks();
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
        Toast.makeText(this, "CLIC", Toast.LENGTH_SHORT).show();
    }
}
