package com.hillman.currencytest.api.model;

/**
 * Created by hllman on 09.02.18.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hillman.currencytest.api.model.Price;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

public class Currency {

    @SerializedName("name")
    @Expose
    @Getter
    public String name;
    @SerializedName("price")
    @Expose
    @Getter
    public Price price;
    @SerializedName("percent_change")
    @Expose
    @Getter
    public Double percentChange;
    @SerializedName("volume")
    @Expose
    @Getter
    public Integer volume;
    @SerializedName("symbol")
    @Expose
    @Getter
    public String symbol;

}


