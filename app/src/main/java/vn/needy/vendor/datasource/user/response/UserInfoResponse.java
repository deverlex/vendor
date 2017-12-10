package vn.needy.vendor.datasource.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.datasource.BaseResponse;
import vn.needy.vendor.model.User;

/**
 * Created by lion on 30/10/2017.
 */

public class UserInfoResponse extends BaseResponse {

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
