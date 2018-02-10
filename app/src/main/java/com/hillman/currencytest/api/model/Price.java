package com.hillman.currencytest.api.model;

/**
 * Created by hllman on 09.02.18.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;


public class Price {

    @SerializedName("currency")
    @Expose
    @Getter
    public String currency;
    @SerializedName("amount")
    @Expose
    @Getter
    public Double amount;

}
