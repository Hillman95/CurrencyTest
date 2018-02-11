package com.hillman.currencytest.mvp;


import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
                .interval(15, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
                    view.listUpdate();
                }).observeOn(Schedulers.io())
                .startWith(0L)
                .flatMap((Long l) -> model.getCurrency())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currencies -> {
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
