package com.hillman.currencytest.mvp;

import java.util.List;
import com.hillman.currencytest.api.ApiService;
import com.hillman.currencytest.api.model.Currency;
import io.reactivex.Observable;

/**
 * Created by hllman on 09.02.18.
 */

public class CurrencyModel {

    public static CurrencyModel instance;

    public CurrencyModel() {
    }

    public static CurrencyModel getInstance() {
        if (instance == null) {
            instance = new CurrencyModel();
        }
        return instance;
    }

    public Observable<List<Currency>> getCurrency() {
        return ApiService.getInstance().getStocks()
                .map(stocks -> {
                    List<Currency> currencies = stocks.currencies;
                    return currencies;
                });
    }



}
