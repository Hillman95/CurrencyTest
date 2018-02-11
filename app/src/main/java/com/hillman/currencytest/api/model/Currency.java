package com.hillman.currencytest.api.model;

/**
 * Created by hllman on 09.02.18.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class Currency {

    @SerializedName("name")
    @Expose
    @Getter
    private String name;
    @SerializedName("price")
    @Expose
    @Getter
    private Price price;
    @SerializedName("percent_change")
    @Expose
    @Getter
    private Double percentChange;
    @SerializedName("volume")
    @Expose
    @Getter
    private Integer volume;
    @SerializedName("symbol")
    @Expose
    @Getter
    private String symbol;

}


