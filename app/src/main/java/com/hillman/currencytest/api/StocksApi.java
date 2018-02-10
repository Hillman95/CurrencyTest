package com.hillman.currencytest.api;

import com.hillman.currencytest.api.model.Stock;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by hllman on 09.02.18.
 */

public interface StocksApi {

    @GET("/stocks.json")
    Observable<Stock> getStocks();

}
