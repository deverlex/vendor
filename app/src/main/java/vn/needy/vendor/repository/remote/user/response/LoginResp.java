package vn.needy.vendor.repository.remote.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.model.wrapper.UserWrapper;
import vn.needy.vendor.port.message.BaseResponse;

/**
 * Created by lion on 11/12/2017.
 */

public class LoginResp extends BaseResponse {

    @Expose
    @SerializedName("user")
    private UserWrapper mUser;
    @Expose
    @SerializedName("token")
    private String mToken;

    public UserWrapper getUser() {
        return mUser;
    }

    public void setUser(UserWrapper user) {
        mUser = user;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }
}
