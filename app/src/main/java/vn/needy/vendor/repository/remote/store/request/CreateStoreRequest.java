package vn.needy.vendor.repository.remote.store.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by truongpq on 16/01/2018.
 */

public class CreateStoreRequest {
    @Expose
    @SerializedName("store_name")
    private String mStoreName;

    @Expose
    @SerializedName("address")
    private String mAddress;

    @Expose
    @SerializedName("lat")
    private double mLat;

    @Expose
    @SerializedName("lng")
    private double mLng;

    @Expose
    @SerializedName("closing_time")
    private String mClosingTime;

    @Expose
    @SerializedName("opening_time")
    private String mOpeningTime;

    public String getStoreName() {
        return mStoreName;
    }

    public void setStoreName(String storeName) {
        mStoreName = storeName;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLng() {
        return mLng;
    }

    public void setLng(double lng) {
        mLng = lng;
    }

    public String getClosingTime() {
        return mClosingTime;
    }

    public void setClosingTime(String closingTime) {
        mClosingTime = closingTime;
    }

    public String getOpeningTime() {
        return mOpeningTime;
    }

    public void setOpeningTime(String openingTime) {
        mOpeningTime = openingTime;
    }
}
