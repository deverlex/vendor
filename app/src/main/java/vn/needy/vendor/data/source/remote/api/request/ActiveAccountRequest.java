package vn.needy.vendor.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 31/10/2017.
 */

public class ActiveAccountRequest {

    @Expose
    @SerializedName("fullName")
    private String mFullName;
    @Expose
    @SerializedName("address")
    private String mAddress;
    @Expose
    @SerializedName("lat")
    private float mLat;
    @Expose
    @SerializedName("lng")
    private float mLng;

    public ActiveAccountRequest() {
        super();
    }

    public ActiveAccountRequest(String fullName, String address, float lat, float lng) {
        mFullName = fullName;
        mAddress = address;
        mLat = lat;
        mLng = lng;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public float getLat() {
        return mLat;
    }

    public void setLat(float lat) {
        mLat = lat;
    }

    public float getLng() {
        return mLng;
    }

    public void setLng(float lng) {
        mLng = lng;
    }
}
