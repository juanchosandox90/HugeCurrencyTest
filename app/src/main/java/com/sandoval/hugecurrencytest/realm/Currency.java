package com.sandoval.hugecurrencytest.realm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;

public class Currency extends RealmObject {

    @SerializedName("median_rate")
    @Expose
    private String medianRate;
    @SerializedName("unit_value")
    @Expose
    private Integer unitValue;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("buying_rate")
    @Expose
    private String buyingRate;
    @SerializedName("selling_rate")
    @Expose
    private String sellingRate;
    private Date downloadDate;

    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(Date downloadDate) {
        this.downloadDate = downloadDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Integer unitValue) {
        this.unitValue = unitValue;
    }

    public String getBuyingRate() {
        return buyingRate;
    }

    public void setBuyingRate(String buyingRate) {
        this.buyingRate = buyingRate;
    }

    public String getMedianRate() {
        return medianRate;
    }

    public void setMedianRate(String medianRate) {
        this.medianRate = medianRate;
    }

    public String getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(String sellingRate) {
        this.sellingRate = sellingRate;
    }
}

/**
 * Old currency model for fixerApi
 * Testing with api: http://hnbex.eu/api/v1/
 * For some reason im not getting the fetched data as i want
 * need more to time to debug the problem but going trought other
 * api to move on im not get stuck
 */

/*
public class Currency extends RealmObject {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rates")
    @Expose
    private Rates rates;

    /**
     * No args constructor for use in serialization
     *
     */
/*
    public Currency() {
    }

    /**
     *
     * @param timestamp
     * @param base
     * @param rates
     * @param date
     * @param success
     */
/*
    public Currency(Boolean success, Integer timestamp, String base, String date, Rates rates) {
        super();
        this.success = success;
        this.timestamp = timestamp;
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

}
*/
