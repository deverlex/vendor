package vn.needy.vendor.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 05/10/2017.
 */

public class LoginRequest extends BaseRequest {

    @Expose
    @SerializedName("fcmToken")
    private String mFcmToken;

    public String getFcmToken() {
        return mFcmToken;
    }

    public void setFcmToken(String mFcmToken) {
        this.mFcmToken = mFcmToken;
    }
}
