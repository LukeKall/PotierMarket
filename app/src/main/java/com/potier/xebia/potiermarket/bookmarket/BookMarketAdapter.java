package com.potier.xebia.potiermarket.bookmarket;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.potier.xebia.potiermarket.R;
import com.potier.xebia.potiermarket.book.Book;

import java.util.List;

public class BookMarketAdapter extends RecyclerView.Adapter<BookMarketAdapter.ViewHolder> {

    private List<Book> bookList;
    private OnBookClickListener onBookClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView bookTitleView;
        private final TextView bookSysnopsisView;
        private final TextView bookPriceView;
        private final Button addCartButton;

        ViewHolder(View v) {
            super(v);
            bookTitleView = v.findViewById(R.id.bookTitle);
            bookSysnopsisView = v.findViewById(R.id.bookSynopsis);
            bookPriceView = v.findViewById(R.id.bookPrice);
            addCartButton = v.findViewById(R.id.addCart);
        }

        void setBindBook(final Book book, final OnBookClickListener onBookClickListener){
            bookTitleView.setText(book.getTitle());
            bookSysnopsisView.setText(book.getSynopsis().get((0)));
            bookPriceView.setText(book.getPrice() + "€");

            itemView.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBookClickListener.onClickBook(book);
                }
            });
            addCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBookClickListener.onClickAddCartButton(book);
                }
            });
        }
    }

    BookMarketAdapter(List<Book> bookList, OnBookClickListener onBookClickListener) {
        this.bookList = bookList;
        this.onBookClickListener = onBookClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.books_market_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.setBindBook(bookList.get(position), onBookClickListener);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
