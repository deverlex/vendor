package vn.needy.vendor.repository.remote.user.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 14/10/2017.
 */

public class RegisterUserRequest {

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
    @SerializedName("language")
    private String mLanguage;

    @Expose
    @SerializedName("phone_token")
    private String mPhoneToken;

    @Expose
    @SerializedName("username")
    private String mPhoneNumber;

    @Expose
    @SerializedName("password")
    private String mPassword;

    public RegisterUserRequest() {
        super();
    }

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

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getPhoneToken() {
        return mPhoneToken;
    }

    public void setPhoneToken(String phoneToken) {
        mPhoneToken = phoneToken;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
