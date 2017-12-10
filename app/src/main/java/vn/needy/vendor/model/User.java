package vn.needy.vendor.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 03/10/2017.
 */

public class User implements Parcelable {

    @SerializedName("state")
    @Expose
    private int mState;
    @SerializedName("fullName")
    @Expose
    private String mFullName;
    @SerializedName("gender")
    @Expose
    private String mGender;
    @SerializedName("address")
    @Expose
    private String mAddress;
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
    @SerializedName("lastResetPassword")
    @Expose
    private String mLastResetPassword;

    protected User(Parcel in) {
        mState = in.readInt();
        mFullName = in.readString();
        mGender = in.readString();
        mAddress = in.readString();
        mEmail = in.readString();
        mBirthday = in.readString();
        mLat = in.readFloat();
        mLng = in.readFloat();
        mCreatedTime = in.readString();
        mLastUpdatedTime = in.readString();
        mLastResetPassword = in.readString();
    }

    public User() {
        super();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mState);
        dest.writeString(mFullName);
        dest.writeString(mGender);
        dest.writeString(mAddress);
        dest.writeString(mEmail);
        dest.writeString(mBirthday);
        dest.writeFloat(mLat);
        dest.writeFloat(mLng);
        dest.writeString(mCreatedTime);
        dest.writeString(mLastUpdatedTime);
        dest.writeString(mLastResetPassword);
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

    public void setState(int state) {
        this.mState = state;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        this.mFullName = fullName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        this.mGender = gender;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public void setBirthday(String birthday) {
        this.mBirthday = birthday;
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

    public String getCreatedTime() {
        return mCreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        this.mCreatedTime = createdTime;
    }

    public String getLastUpdatedTime() {
        return mLastUpdatedTime;
    }

    public void setLastUpdatedTime(String lastUpdatedTime) {
        this.mLastUpdatedTime = lastUpdatedTime;
    }

    public String getLastResetPassword() {
        return mLastResetPassword;
    }

    public void setLastResetPassword(String lastResetPassword) {
        this.mLastResetPassword = lastResetPassword;
    }
}
