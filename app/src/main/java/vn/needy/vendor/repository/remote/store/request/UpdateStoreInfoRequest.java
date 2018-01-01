package vn.needy.vendor.repository.remote.store.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.domain.Store;

/**
 * Created by lion on 12/12/2017.
 */

public class UpdateStoreInfoRequest {
    @Expose
    @SerializedName("name")
    private String mName;

    @Expose
    @SerializedName("address")
    private String mAddress;

    @Expose
    @SerializedName("description")
    private String mDescription;

    @Expose
    @SerializedName("email")
    private String mEmail;

    @Expose
    @SerializedName("lat")
    private float mLat;

    @Expose
    @SerializedName("lng")
    private float mLng;

    @Expose
    @SerializedName("openingTime")
    private String mOpeningTime;

    @Expose
    @SerializedName("closingTime")
    private String mClosingTime;

    public UpdateStoreInfoRequest(Store store) {
        mName = store.getName();
        mAddress = store.getAddress();
        mDescription = store.getDescription();
        mEmail = store.getEmail();
        mLat = store.getLat();
        mLng = store.getLng();
        mOpeningTime = store.getOpeningTime();
        mClosingTime = store.getClosingTime();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public float getLat() {
        return mLat;
    }

    public void setLat(float lat) {
        this.mLat = lat;
    }

    public float getLng() {
        return mLng;
    }

    public void setLng(float lng) {
        this.mLng = lng;
    }

    public String getOpeningTime() {
        return mOpeningTime;
    }

    public void setOpeningTime(String openingTime) {
        this.mOpeningTime = openingTime;
    }

    public String getClosingTime() {
        return mClosingTime;
    }

    public void setClosingTime(String closingTime) {
        this.mClosingTime = closingTime;
    }
}
