package com.sandoval.hugecurrencytest.realm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Rates extends RealmObject {

    @SerializedName("BRL")
    @Expose
    private Double bRL;
    @SerializedName("EUR")
    @Expose
    private Integer eUR;
    @SerializedName("GBP")
    @Expose
    private Double gBP;
    @SerializedName("JPY")
    @Expose
    private Double jPY;
    @SerializedName("USD")
    @Expose
    private Double uSD;


    /**
     * No args constructor for use in serialization
     */
    public Rates() {
    }

    /**
     * @param uSD
     * @param gBP
     * @param bRL
     * @param eUR
     * @param jPY
     */
    public Rates(Double bRL, Integer eUR, Double gBP, Double jPY, Double uSD) {
        super();

        this.bRL = bRL;
        this.eUR = eUR;
        this.gBP = gBP;
        this.jPY = jPY;
        this.uSD = uSD;
    }

    public Double getBRL() {
        return bRL;
    }

    public void setBRL(Double bRL) {
        this.bRL = bRL;
    }


    public Integer getEUR() {
        return eUR;
    }

    public void setEUR(Integer eUR) {
        this.eUR = eUR;
    }


    public Double getGBP() {
        return gBP;
    }

    public void setGBP(Double gBP) {
        this.gBP = gBP;
    }


    public Double getUSD() {
        return uSD;
    }

    public void setUSD(Double uSD) {
        this.uSD = uSD;
    }

}
