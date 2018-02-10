package com.hillman.currencytest.mvp;


import com.hillman.currencytest.api.model.Currency;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hllman on 10.02.18.
 */

public class CurrencyPresenter {

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
    }


    public void viewIsReady() {
        loadCurrenciesList();
    }

    public void loadCurrenciesList() {
        model.getCurrency().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Currency>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(List<Currency> currencies) {
                        if (view!=null){
                        view.listInit(currencies);}
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });

    }


    public void updateCurrenciesList(){
        if (view!=null){
        view.listUpdate();
        loadCurrenciesList();}
    }

}
