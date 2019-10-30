package com.judacribz.amazonbooks.model.dagger.modules;

import com.judacribz.amazonbooks.model.datasource.remote.retrofit.AmazonBooksService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.judacribz.amazonbooks.model.constants.Api.BASE_URL;

@Module
public class RetrofitModule {

    @Singleton
    @Provides
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);

        return loggingInterceptor;
    }

    @Singleton
    @Provides
    OkHttpClient providesOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    Retrofit getAmazonBooksRetrofitInstance(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    AmazonBooksService getObservableAmazonBooksData(Retrofit retrofitInstance) {
        return retrofitInstance.create(AmazonBooksService.class);
    }
}
