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
    @SerializedName("username")
    @Expose
    private String mPhoneNumber;
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
    @SerializedName("avatar")
    @Expose
    private String mAvatar;
    @SerializedName("coverPicture")
    @Expose
    private String mCoverPicture;
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
        mPhoneNumber = in.readString();
        mFirstName = in.readString();
        mLastName = in.readString();
        mGender = in.readString();
        mAddress = in.readString();
        mAvatar = in.readString();
        mCoverPicture = in.readString();
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
        dest.writeString(mPhoneNumber);
        dest.writeString(mFirstName);
        dest.writeString(mLastName);
        dest.writeString(mGender);
        dest.writeString(mAddress);
        dest.writeString(mAvatar);
        dest.writeString(mCoverPicture);
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

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
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

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        this.mAvatar = avatar;
    }

    public String getCoverPicture() {
        return mCoverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.mCoverPicture = coverPicture;
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
