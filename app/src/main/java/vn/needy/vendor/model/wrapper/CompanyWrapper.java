package vn.needy.vendor.model.wrapper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 11/12/2017.
 */

public class CompanyWrapper {

    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("companyNumber")
    @Expose
    private String mCompanyNumber;
    @SerializedName("state")
    @Expose
    private int mState;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("address")
    @Expose
    private String mAddress;
    @SerializedName("foundedDate")
    @Expose
    private String mFoundedDate;
    @SerializedName("openingTime")
    @Expose
    private String mOpeningTime;
    @SerializedName("closingTime")
    @Expose
    private String mClosingTime;
    @SerializedName("description")
    @Expose
    private String mDescription;
    @SerializedName("siteUrl")
    @Expose
    private String mSiteUrl;
    @SerializedName("createdTime")
    @Expose
    private String mCreatedTime;
    @SerializedName("lastUpdatedTime")
    @Expose
    private String mLastUpdatedTime;
    @SerializedName("reputation")
    @Expose
    private boolean mIsReputation;
    @SerializedName("email")
    @Expose
    private String mEmail;
    @SerializedName("lat")
    @Expose
    private float mLat;
    @SerializedName("lng")
    @Expose
    private float mLng;

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        mId = mId;
    }

    public String getCompanyNumber() {
        return mCompanyNumber;
    }

    public void setCompanyNumber(String mAuthId) {
        mCompanyNumber = mAuthId;
    }

    public int getState() {
        return mState;
    }

    public void setState(int mState) {
        mState = mState;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        mName = mName;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getFoundedDate() {
        return mFoundedDate;
    }

    public void setFoundedDate(String mFoundedDate) {
        mFoundedDate = mFoundedDate;
    }

    public String getOpeningTime() {
        return mOpeningTime;
    }

    public void setOpeningTime(String mOpeningTime) {
        mOpeningTime = mOpeningTime;
    }

    public String getClosingTime() {
        return mClosingTime;
    }

    public void setClosingTime(String mClosingTime) {
        mClosingTime = mClosingTime;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getSiteUrl() {
        return mSiteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        mSiteUrl = siteUrl;
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

    public void setLastUpdatedTime(String mLastUpdatedTime) {
        mLastUpdatedTime = mLastUpdatedTime;
    }

    public boolean isIsReputation() {
        return mIsReputation;
    }

    public void setIsReputation(boolean isReputation) {
        mIsReputation = isReputation;
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
}
