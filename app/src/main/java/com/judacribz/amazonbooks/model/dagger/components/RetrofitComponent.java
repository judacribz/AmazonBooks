package com.judacribz.amazonbooks.model.dagger.components;

import com.judacribz.amazonbooks.model.dagger.modules.RetrofitModule;
import com.judacribz.amazonbooks.viewmodel.AmazonBooksViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface RetrofitComponent {
    void inject(AmazonBooksViewModel amazonBooksViewModel);
}
