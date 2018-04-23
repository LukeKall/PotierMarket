package com.potier.xebia.potiermarket.book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookApi {

    String ENDPOINT = "http://henri-potier.xebia.fr";

    @GET("/books")
    Call<List<Book>> listBooks();

    @GET("/books/{ISBNS}/commercialOffers")
    Call<OfferResponse> listOffers(@Path("ISBNS") String ISBNS);

    BookApi bookApi = new Retrofit.Builder()
            .baseUrl(BookApi.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApi.class);
}
