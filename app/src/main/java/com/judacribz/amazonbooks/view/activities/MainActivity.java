package com.judacribz.amazonbooks.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.judacribz.amazonbooks.R;
import com.judacribz.amazonbooks.databinding.ActivityMainBinding;
import com.judacribz.amazonbooks.viewmodel.AmazonBooksViewModel;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {
    private AmazonBooksViewModel amazonBooksViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );
        activityMainBinding.setViewmodel(amazonBooksViewModel = new AmazonBooksViewModel());
        activityMainBinding.setLifecycleOwner(this);

        amazonBooksViewModel.getMutableBookList().observe(
                this,
                books -> amazonBooksViewModel.updateAdapter(books)
        );

        amazonBooksViewModel.requestAmazonBooks();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(amazonBooksViewModel);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(amazonBooksViewModel);
    }
}
