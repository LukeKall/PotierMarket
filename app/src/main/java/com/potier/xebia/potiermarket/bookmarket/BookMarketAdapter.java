package com.potier.xebia.potiermarket.bookmarket;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.potier.xebia.potiermarket.R;
import com.potier.xebia.potiermarket.book.Book;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BookMarketAdapter extends RecyclerView.Adapter<BookMarketAdapter.ViewHolder> {

    private List<Book> bookList;
    private OnBookClickListener onBookClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView bookTitleView;
        private final TextView bookSysnopsisView;
        private final TextView bookPriceView;
        private final Button addCartButton;
        private final ImageView imageView;

        ViewHolder(View v) {
            super(v);
            bookTitleView = v.findViewById(R.id.book_title);
            bookSysnopsisView = v.findViewById(R.id.book_synopsis);
            bookPriceView = v.findViewById(R.id.book_price);
            addCartButton = v.findViewById(R.id.add_cart);
            imageView = v.findViewById(R.id.book_image);
        }

        void setBindBook(final Book book, final OnBookClickListener onBookClickListener) throws IOException {
            bookTitleView.setText(book.getTitle());
            bookSysnopsisView.setText(book.getSynopsis().get((0)));
            bookPriceView.setText(book.getPrice() + "€");
            Picasso.get().load(book.getCover()).into(imageView);

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
        try {
            viewHolder.setBindBook(bookList.get(position), onBookClickListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
