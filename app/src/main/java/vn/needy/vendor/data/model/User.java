package vn.needy.vendor.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lion on 03/10/2017.
 */

public class User extends RealmObject implements Parcelable {
    @PrimaryKey
    @SerializedName("phoneNumber")
    @Expose
    private String mPhoneNumber;
    @SerializedName("state")
    @Expose
    private int mState;
    @SerializedName("timeUnlock")
    @Expose
    private int mTimeUnlock;
    @SerializedName("firstName")
    @Expose
    private String mFirstName;
    @SerializedName("lastName")
    @Expose
    private String mLastName;
    @SerializedName("gender")
    @Expose
    private String mGender;
    @SerializedName("address")
    @Expose
    private String mAddress;
    @SerializedName("avatarUrl")
    @Expose
    private String mAvatarUrl;
    @SerializedName("coverImageUrl")
    @Expose
    private String mCoverImageUrl;
    @SerializedName("email")
    @Expose
    private String mEmail;
    @SerializedName("birthday")
    @Expose
    private String mBirthday;
    @SerializedName("lat")
    @Expose
    private float mLat;
    @SerializedName("lng")
    @Expose
    private float mLng;
    @SerializedName("createdTime")
    @Expose
    private String mCreatedTime;
    @SerializedName("lastUpdatedTime")
    @Expose
    private String mLastUpdatedTime;

    protected User(Parcel in) {
        mState = in.readInt();
        mTimeUnlock = in.readInt();
        mPhoneNumber = in.readString();
        mFirstName = in.readString();
        mLastName = in.readString();
        mGender = in.readString();
        mAddress = in.readString();
        mAvatarUrl = in.readString();
        mCoverImageUrl = in.readString();
        mEmail = in.readString();
        mBirthday = in.readString();
        mLat = in.readFloat();
        mLng = in.readFloat();
        mCreatedTime = in.readString();
        mLastUpdatedTime = in.readString();
    }

    public User() {
        super();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mState);
        dest.writeInt(mTimeUnlock);
        dest.writeString(mPhoneNumber);
        dest.writeString(mFirstName);
        dest.writeString(mLastName);
        dest.writeString(mGender);
        dest.writeString(mAddress);
        dest.writeString(mAvatarUrl);
        dest.writeString(mCoverImageUrl);
        dest.writeString(mEmail);
        dest.writeString(mBirthday);
        dest.writeFloat(mLat);
        dest.writeFloat(mLng);
        dest.writeString(mCreatedTime);
        dest.writeString(mLastUpdatedTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getState() {
        return mState;
    }

    public void setState(int mState) {
        this.mState = mState;
    }

    public int getTimeUnlock() {
        return mTimeUnlock;
    }

    public void setTimeUnlock(int mTimeUnlock) {
        this.mTimeUnlock = mTimeUnlock;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String mAvatarUrl) {
        this.mAvatarUrl = mAvatarUrl;
    }

    public String getCoverImageUrl() {
        return mCoverImageUrl;
    }

    public void setCoverImageUrl(String mCoverImageUrl) {
        this.mCoverImageUrl = mCoverImageUrl;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public void setBirthday(String mBirthday) {
        this.mBirthday = mBirthday;
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

    public String getCreatedTime() {
        return mCreatedTime;
    }

    public void setCreatedTime(String mCreatedTime) {
        this.mCreatedTime = mCreatedTime;
    }

    public String getLastUpdatedTime() {
        return mLastUpdatedTime;
    }

    public void setLastUpdatedTime(String mLastUpdatedTime) {
        this.mLastUpdatedTime = mLastUpdatedTime;
    }
}
