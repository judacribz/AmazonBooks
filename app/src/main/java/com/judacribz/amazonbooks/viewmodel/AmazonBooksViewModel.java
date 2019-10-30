package com.judacribz.amazonbooks.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.judacribz.amazonbooks.BR;
import com.judacribz.amazonbooks.model.amazonbooksresponse.Book;
import com.judacribz.amazonbooks.model.dagger.components.DaggerRetrofitComponent;
import com.judacribz.amazonbooks.model.dagger.components.RetrofitComponent;
import com.judacribz.amazonbooks.model.datasource.remote.retrofit.AmazonBooksObserver;
import com.judacribz.amazonbooks.model.datasource.remote.retrofit.AmazonBooksService;
import com.judacribz.amazonbooks.model.event.AmazonBooksEvent;
import com.judacribz.amazonbooks.view.adapters.AmazonBooksAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.judacribz.amazonbooks.model.datasource.remote.retrofit.AmazonBooksRetrofitHelper.getObservableAmazonBooksData;

public class AmazonBooksViewModel extends BaseObservable {
    @Inject
    AmazonBooksService amazonBooksService;

    private final MutableLiveData<List<Book>> booksList = new MutableLiveData<>();

    public AmazonBooksViewModel() {
        DaggerRetrofitComponent.builder().build().inject(this);

        amazonBooksService
                .getAmazonBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AmazonBooksObserver());
    }

    @Bindable
    public AmazonBooksAdapter adapter = null;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAmazonBooksEventReceived(AmazonBooksEvent event) {
        booksList.setValue(event.getBooks());
    }

    public MutableLiveData<List<Book>> getBooksList() {
        return booksList;
    }

    public void updateAdapter(List<Book> books) {
        if (adapter == null) {
            adapter = new AmazonBooksAdapter(books);
            notifyPropertyChanged(BR.adapter);
        } else {
            adapter.setBooks(books);
        }
    }
}
