package com.potier.xebia.potiermarket.bookmarket;

import com.potier.xebia.potiermarket.book.Book;

import java.util.List;

public interface BookMarketPresenter {

    void getAllBooks();
    void addBookInCart(Book book);
    List<Book> getCart();
}
