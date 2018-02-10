package com.hillman.currencytest.api.model;

/**
 * Created by hllman on 09.02.18.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class Stock {

    @SerializedName("stock")
    @Expose
    @Getter
    public List<Currency> currencies = null;
    @SerializedName("as_of")
    @Expose
    @Getter
    public String asOf;

}