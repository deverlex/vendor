package vn.needy.vendor.model.wrapper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreWrapper {

    @Expose
    @SerializedName("id")
    private String mId;
    @Expose
    @SerializedName("state")
    private int mState;
    @Expose
    @SerializedName("unlockTime")
    private String mUnlockTime;
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
    @Expose
    @SerializedName("createdTime")
    private String mCreatedTime;
    @Expose
    @SerializedName("lastUpdatedTime")
    private String mLastUpdatedTime;
    @Expose
    @SerializedName("lastUpdatedBy")
    private String mLastUpdatedBy;

    public String getId() {
        return mId;
    }

    public void setmId(String id) {
        mId = id;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        mState = state;
    }

    public String getUnlockTime() {
        return mUnlockTime;
    }

    public void setUnlockTime(String unlockTime) {
        mUnlockTime = unlockTime;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
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

    public String getOpeningTime() {
        return mOpeningTime;
    }

    public void setOpeningTime(String openingTime) {
        mOpeningTime = openingTime;
    }

    public String getClosingTime() {
        return mClosingTime;
    }

    public void setClosingTime(String closingTime) {
        mClosingTime = closingTime;
    }

    public String getCreatedTime() {
        return mCreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        mCreatedTime = createdTime;
    }

    public String getLastUpdatedTime() {
        return mLastUpdatedTime;
    }

    public void setLastUpdatedTime(String lastUpdatedTime) {
        mLastUpdatedTime = lastUpdatedTime;
    }

    public String getLastUpdatedBy() {
        return mLastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        mLastUpdatedBy = lastUpdatedBy;
    }
}
