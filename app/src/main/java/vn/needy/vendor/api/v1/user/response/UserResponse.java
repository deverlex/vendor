package vn.needy.vendor.api.v1.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.database.model.User;

/**
 * Created by lion on 30/10/2017.
 */

public class UserResponse extends BaseResponse {

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
