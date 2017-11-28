package vn.needy.vendor.api.v1.company.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 08/10/2017.
 */

public class RegisterCompanyRequest {
    @Expose
    @SerializedName("fcmToken")
    private String mFcmToken;
    @Expose
    @SerializedName("companyName")
    private String mCompanyName;
    @Expose
    @SerializedName("officeAddress")
    private String mOfficeAddress;
    @Expose
    @SerializedName("storeName")
    private String mStoreName;
    @Expose
    @SerializedName("storeAddress")
    private String mStoreAddress;
    @Expose
    @SerializedName("lat")
    private float mLat;
    @Expose
    @SerializedName("lng")
    private float mLng;

    public String getFcmToken() {
        return mFcmToken;
    }

    public void setFcmToken(String mFcmToken) {
        this.mFcmToken = mFcmToken;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String mCompanyName) {
        this.mCompanyName = mCompanyName;
    }

    public String getOfficeAddress() {
        return mOfficeAddress;
    }

    public void setOfficeAddress(String mOfficeAddress) {
        this.mOfficeAddress = mOfficeAddress;
    }

    public String getStoreName() {
        return mStoreName;
    }

    public void setStoreName(String mStoreName) {
        this.mStoreName = mStoreName;
    }

    public String getStoreAddress() {
        return mStoreAddress;
    }

    public void setStoreAddress(String mStoreAddress) {
        this.mStoreAddress = mStoreAddress;
    }

    public float getLat() {
        return mLat;
    }

    public void setLat(float mLat) {
        this.mLat = mLat;
    }

    public float getLng() {
        return mLng;
    }

    public void setLng(float mLng) {
        this.mLng = mLng;
    }
}
