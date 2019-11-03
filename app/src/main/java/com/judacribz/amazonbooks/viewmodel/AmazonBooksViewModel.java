package com.judacribz.amazonbooks.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.judacribz.amazonbooks.BR;
import com.judacribz.amazonbooks.model.amazonbooksresponse.Book;
import com.judacribz.amazonbooks.model.dagger.components.DaggerRetrofitComponent;
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

public class AmazonBooksViewModel extends BaseObservable {

    private final MutableLiveData<List<Book>> mutableBookList = new MutableLiveData<>();

    @Inject
    AmazonBooksService amazonBooksService;

    @Bindable
    public AmazonBooksAdapter adapter = null;

    public AmazonBooksViewModel() {
        DaggerRetrofitComponent.builder().build().inject(this);
    }

    public void requestAmazonBooks() {
        amazonBooksService
                .getAmazonBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new AmazonBooksObserver());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAmazonBooksEventReceived(AmazonBooksEvent event) {
        mutableBookList.setValue(event.getBooks());
    }

    public MutableLiveData<List<Book>> getMutableBookList() {
        return mutableBookList;
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
