package vn.needy.vendor.repository.remote.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.model.User;

/**
 * Created by lion on 30/10/2017.
 */

public class UserInfoResponse {

    @Expose
    @SerializedName("user")
    private User mUser;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
