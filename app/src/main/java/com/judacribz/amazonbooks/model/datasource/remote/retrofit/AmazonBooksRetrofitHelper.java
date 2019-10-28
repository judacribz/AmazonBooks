package com.judacribz.amazonbooks.model.datasource.remote.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.judacribz.amazonbooks.model.constants.Api.BASE_URL;

public class AmazonBooksRetrofitHelper {
    private static OkHttpClient getOkHttpClient() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    private static Retrofit getAmazonBooksRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    public static AmazonBooksService getObservableAmazonBooksData() {
        return getAmazonBooksRetrofitInstance().create(AmazonBooksService.class);
    }
}
