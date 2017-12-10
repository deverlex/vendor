package vn.needy.vendor.datasource.user.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 17/10/2017.
 */

public class ResetPasswordRequest {

    @Expose
    @SerializedName("firebaseToken")
    private String mFirebaseToken;
    @Expose
    @SerializedName(("password"))
    private String mPassword;

    public String getFirebaseToken() {
        return mFirebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        mFirebaseToken = firebaseToken;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
