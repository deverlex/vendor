package vn.needy.vendor.repository.remote.user.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 05/10/2017.
 */

public class LoginRequest {

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
    @SerializedName("username")
    private String mPhoneNumber;

    @Expose
    @SerializedName("password")
    private String mPassWord;

    public LoginRequest() {
        super();
    }

    public LoginRequest(String phoneNumber, String passWord) {
        mPhoneNumber = phoneNumber;
        mPassWord = passWord;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
    }

    public String getPassWord() {
        return mPassWord;
    }

    public void setPassWord(String passWord) {
        this.mPassWord = passWord;
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
}
