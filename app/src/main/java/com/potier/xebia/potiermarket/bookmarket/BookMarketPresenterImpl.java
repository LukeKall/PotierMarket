package com.potier.xebia.potiermarket.bookmarket;

import com.potier.xebia.potiermarket.book.Book;
import com.potier.xebia.potiermarket.book.BookApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookMarketPresenterImpl implements BookMarketPresenter {

    private BookMarketView bookMarketView;
    private List<Book> cart = new ArrayList<>();
    private Call callListBooks;

    BookMarketPresenterImpl(BookMarketView bookMarketView) {
        this.bookMarketView = bookMarketView;
    }

    @Override
    public void getAllBooks() {
        BookApi bookService = BookApi.bookApi;

        callListBooks = bookService.listBooks();

        callListBooks.enqueue(new Callback<List<Book>>() {

            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                bookMarketView.showBooks(response.body());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {

            }
        });
    }

    @Override
    public void addBookInCart(Book book) {
        cart.add(book);
    }

    @Override
    public List<Book> getCart() {
        return cart;
    }

    @Override
    public void onDestroy() {
        callListBooks.cancel();
    }


}
