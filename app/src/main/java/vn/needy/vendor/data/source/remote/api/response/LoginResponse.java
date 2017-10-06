package vn.needy.vendor.data.source.remote.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.data.model.Auth;
import vn.needy.vendor.data.model.User;

public class LoginResponse implements Parcelable {

    private static final String TAG = LoginResponse.class.getName();
    @Expose
    @SerializedName("auth")
    private Auth mAuth;
    @Expose
    @SerializedName("user")
    private User mUser;
    @Expose
    @SerializedName("state")
    private int mState;
    @Expose
    @SerializedName("message")
    private String mMessage;

    protected LoginResponse(Parcel in) {
        mAuth = in.readParcelable(Auth.class.getClassLoader());
        mUser = in.readParcelable(User.class.getClassLoader());
        mState = in.readInt();
        mMessage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mAuth, flags);
        dest.writeParcelable(mUser, flags);
        dest.writeInt(mState);
        dest.writeString(mMessage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    public Auth getAuth() {
        return mAuth;
    }

    public void setAuth(Auth mAuth) {
        this.mAuth = mAuth;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public int getState() {
        return mState;
    }

    public void setState(int status) {
        mState = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
