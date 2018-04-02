package com.potier.xebia.potiermarket.book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface BookApi {

    String ENDPOINT = "http://henri-potier.xebia.fr";

    @GET("/books")
    Call<List<Book>> listBooks();

    BookApi bookApi = new Retrofit.Builder()
            .baseUrl(BookApi.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApi.class);
}
