package com.potier.xebia.potiermarket.bookmarket;


import com.potier.xebia.potiermarket.book.Book;

public interface OnBookClickListener {

    void onClickAddCartButton(Book book);

    void onClickBook(Book book);
}
