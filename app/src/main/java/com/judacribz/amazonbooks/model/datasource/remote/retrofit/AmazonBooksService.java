package com.judacribz.amazonbooks.model.datasource.remote.retrofit;

import com.judacribz.amazonbooks.model.amazonbooksresponse.Book;

import java.util.List;

import io.reactivex.Single;

import retrofit2.http.GET;

import static com.judacribz.amazonbooks.model.constants.Api.BOOKS_QUERY;

public interface AmazonBooksService {

    @GET(BOOKS_QUERY)
    Single<List<Book>> getAmazonBooks();
}
