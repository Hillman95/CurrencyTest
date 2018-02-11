package com.hillman.currencytest.mvp;


import android.util.Log;

import com.hillman.currencytest.api.model.Currency;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hllman on 10.02.18.
 */

public class CurrencyPresenter {

    Disposable disposable;
    CurrencyView view;
    CurrencyModel model;

    public CurrencyPresenter(CurrencyModel model) {
        this.model = model;
    }

    public void attachView(CurrencyView currencyView) {
        view = currencyView;
    }

    public void detachView() {
        view = null;
        dispose(disposable);
    }


    public void viewIsReady() {
        loadCurrenciesList();
    }

    public void loadCurrenciesList() {
        dispose(disposable);

        disposable = Observable
                .interval(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
                    view.listUpdate();
                }).observeOn(Schedulers.io())
                .startWith(0L)
                .flatMap((Long l) -> model.getCurrency())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currencies -> {
                            System.out.println("list");
                            if (view != null) {
                                view.listInit(currencies);
                            }
                        }, throwable -> {});

    }


    public void updateCurrenciesList() {
        if (view != null) {
            view.listUpdate();
            loadCurrenciesList();
        }
    }

    private void dispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
