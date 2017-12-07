package vn.needy.vendor.database.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Company implements Parcelable {

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
    private String mOfficeAddress;
    @SerializedName("foundedDate")
    @Expose
    private String mFoundedDate;
    @SerializedName("openingTime")
    @Expose
    private String mOpeningTime;
    @SerializedName("closingTime")
    @Expose
    private String mClosingTime;
    @SerializedName("avatar")
    @Expose
    private String mAvatar;
    @SerializedName("pictures")
    @Expose
    private String mPictures;
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

    public Company() {
        super();
    }

    protected Company(Parcel in) {
        mId = in.readString();
        mCompanyNumber = in.readString();
        mState = in.readInt();
        mName = in.readString();
        mOfficeAddress = in.readString();
        mFoundedDate = in.readString();
        mOpeningTime = in.readString();
        mClosingTime = in.readString();
        mAvatar = in.readString();
        mPictures = in.readString();
        mDescription = in.readString();
        mSiteUrl = in.readString();
        mCreatedTime = in.readString();
        mLastUpdatedTime = in.readString();
        mEmail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mCompanyNumber);
        dest.writeInt(mState);
        dest.writeString(mName);
        dest.writeString(mOfficeAddress);
        dest.writeString(mFoundedDate);
        dest.writeString(mOpeningTime);
        dest.writeString(mClosingTime);
        dest.writeString(mAvatar);
        dest.writeString(mDescription);
        dest.writeString(mSiteUrl);
        dest.writeString(mCreatedTime);
        dest.writeString(mLastUpdatedTime);
        dest.writeString(mEmail);
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
        this.mId = mId;
    }

    public String getCompanyNumber() {
        return mCompanyNumber;
    }

    public void setCompanyNumber(String mAuthId) {
        this.mCompanyNumber = mAuthId;
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

    public String getOfficeAddress() {
        return mOfficeAddress;
    }

    public void setOfficeAddress(String mOfficeAddress) {
        this.mOfficeAddress = mOfficeAddress;
    }

    public String getFoundedDate() {
        return mFoundedDate;
    }

    public void setFoundedDate(String mFoundedDate) {
        this.mFoundedDate = mFoundedDate;
    }

    public String getOpeningTime() {
        return mOpeningTime;
    }

    public void setOpeningTime(String mOpeningTime) {
        this.mOpeningTime = mOpeningTime;
    }

    public String getClosingTime() {
        return mClosingTime;
    }

    public void setClosingTime(String mClosingTime) {
        this.mClosingTime = mClosingTime;
    }

    public String getUrlImage() {
        return mAvatar;
    }

    public void setUrlImage(String mUrlImage) {
        this.mAvatar = mUrlImage;
    }

    public String getCollectionImage() {
        return mPictures;
    }

    public void setCollectionImage(String mCollectionImage) {
        this.mPictures = mCollectionImage;
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
}
