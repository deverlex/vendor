package vn.needy.vendor.repository.remote.user.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.repository.remote.user.context.UpdateUserContext;

/**
 * Created by truongpq on 06/12/2017.
 */

public class UpdateUserInfoRequest {

    @Expose
    @SerializedName("user")
    private UpdateUserContext user;

    public UpdateUserContext getUser() {
        return user;
    }

    public void setUser(UpdateUserContext user) {
        this.user = user;
    }
}
