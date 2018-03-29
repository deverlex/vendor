package vn.needy.vendor.repository.remote.user.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 17/10/2017.
 */

public class ResetAccountRequest {

    @Expose
    @SerializedName("scope")
    private String mScope;

    @Expose
    @SerializedName("instance_id")
    private String mInstanceId;

    @Expose
    @SerializedName("firebase_token")
    private String mFirebaseToken;

    @Expose
    @SerializedName("phone_token")
    private String mPhoneToken;

    @Expose
    @SerializedName(("password"))
    private String mPassword;

    public String getScope() {
        return mScope;
    }

    public void setScope(String scope) {
        mScope = scope;
    }

    public String getInstanceId() {
        return mInstanceId;
    }

    public void setInstanceId(String instanceId) {
        mInstanceId = instanceId;
    }

    public String getFirebaseToken() {
        return mFirebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        mFirebaseToken = firebaseToken;
    }

    public String getPhoneToken() {
        return mPhoneToken;
    }

    public void setPhoneToken(String phoneToken) {
        mPhoneToken = phoneToken;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
