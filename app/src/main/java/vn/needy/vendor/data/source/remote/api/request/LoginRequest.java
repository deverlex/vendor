package vn.needy.vendor.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 05/10/2017.
 */

public class LoginRequest {

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
}
