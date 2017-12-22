package vn.needy.vendor.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import vn.needy.vendor.model.wrapper.CompanyWrapper;

public class Company extends RealmObject implements Parcelable {

    /** Core */
    @PrimaryKey
    private String mId;
    private String mCompanyNumber;
    private int mState;
    private String mName;
    private String mAddress;
    private String mFoundedDate;
    private String mOpeningTime;
    private String mClosingTime;
    private String mDescription;
    private String mSiteUrl;
    private String mCreatedTime;
    private String mLastUpdatedTime;
    private boolean mIsReputation;
    private String mEmail;
    private float mLat;
    private float mLng;

    /** Extends */
    private int mTotalStaff;

    /** Relations */
    private RealmList<FeeTransport> mFeeTransports;

    public Company() {
        super();
    }

    public Company(CompanyWrapper wrapper) {
        mId = wrapper.getId();
        mName = wrapper.getName();
        mCompanyNumber = wrapper.getCompanyNumber();
        mState = wrapper.getState();
        mAddress = wrapper.getAddress();
        mFoundedDate = wrapper.getFoundedDate();
        mOpeningTime = wrapper.getOpeningTime();
        mClosingTime = wrapper.getClosingTime();
        mDescription = wrapper.getDescription();
        mSiteUrl = wrapper.getSiteUrl();
        mCreatedTime = wrapper.getCreatedTime();
        mLastUpdatedTime = wrapper.getLastUpdatedTime();
        mEmail = wrapper.getEmail();
        mLat = wrapper.getLat();
        mLng = wrapper.getLng();
    }

    protected Company(Parcel in) {
        mId = in.readString();
        mCompanyNumber = in.readString();
        mState = in.readInt();
        mName = in.readString();
        mAddress = in.readString();
        mFoundedDate = in.readString();
        mOpeningTime = in.readString();
        mClosingTime = in.readString();
        mDescription = in.readString();
        mSiteUrl = in.readString();
        mCreatedTime = in.readString();
        mLastUpdatedTime = in.readString();
        mEmail = in.readString();
        mLat = in.readFloat();
        mLng = in.readFloat();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mCompanyNumber);
        dest.writeInt(mState);
        dest.writeString(mName);
        dest.writeString(mAddress);
        dest.writeString(mFoundedDate);
        dest.writeString(mOpeningTime);
        dest.writeString(mClosingTime);
        dest.writeString(mDescription);
        dest.writeString(mSiteUrl);
        dest.writeString(mCreatedTime);
        dest.writeString(mLastUpdatedTime);
        dest.writeString(mEmail);
        dest.writeFloat(mLat);
        dest.writeFloat(mLng);
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

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
        this.mState = mState;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
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
        this.mLastUpdatedTime = mLastUpdatedTime;
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

    public int getTotalStaff() {
        return mTotalStaff;
    }

    public void setTotalStaff(int totalStaff) {
        mTotalStaff = totalStaff;
    }

    public RealmList<FeeTransport> getFeeTransports() {
        return mFeeTransports;
    }

    public void setFeeTransports(RealmList<FeeTransport> feeTransports) {
        mFeeTransports = feeTransports;
    }
}
