package vn.needy.vendor.model.wrapper;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 11/12/2017.
 */

public class UserWrapper implements Parcelable {

    @SerializedName("id")
    @Expose
    private String mId;
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

    protected UserWrapper(Parcel in) {
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

    public static final Creator<UserWrapper> CREATOR = new Parcelable.Creator<UserWrapper>() {
        @Override
        public UserWrapper createFromParcel(Parcel in) {
            return new UserWrapper(in);
        }

        @Override
        public UserWrapper[] newArray(int size) {
            return new UserWrapper[size];
        }
    };

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        mState = state;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public void setBirthday(String birthday) {
        mBirthday = birthday;
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
        mLastUpdatedTime =lastUpdatedTime;
    }

    public String getLastResetPassword() {
        return mLastResetPassword;
    }

    public void setLastResetPassword(String lastResetPassword) {
        mLastResetPassword = lastResetPassword;
    }
}
