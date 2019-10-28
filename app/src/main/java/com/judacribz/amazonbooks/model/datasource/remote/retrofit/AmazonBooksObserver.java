package com.judacribz.amazonbooks.model.datasource.remote.retrofit;

import android.util.Log;

import com.judacribz.amazonbooks.model.amazonbooksresponse.Book;
import com.judacribz.amazonbooks.model.event.AmazonBooksEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class AmazonBooksObserver implements SingleObserver<List<Book>> {
    private static final String TAG = AmazonBooksObserver.class.getSimpleName();
    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");
    }

    @Override
    public void onSuccess(List<Book> books) {
        Log.d(TAG, "onSuccess: ");

        EventBus.getDefault().post(new AmazonBooksEvent(books));
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
    }
}
