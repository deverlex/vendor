package vn.needy.vendor.repository.remote.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.domain.User;
import vn.needy.vendor.repository.remote.user.context.UserContext;
import vn.needy.vendor.repository.remote.user.context.UserLocationContext;

/**
 * Created by lion on 30/10/2017.
 */

public class UserInfoResponse {

    @Expose
    @SerializedName("user")
    private UserContext user;

    private List<Long> images;

    @Expose
    @SerializedName("user_location")
    private List<UserLocationContext> locations;

    public UserContext getUser() {
        return user;
    }

    public void setUser(UserContext user) {
        this.user = user;
    }

    public List<Long> getImages() {
        return images;
    }

    public void setImages(List<Long> images) {
        this.images = images;
    }

    public List<UserLocationContext> getLocations() {
        return locations;
    }

    public void setLocations(List<UserLocationContext> locations) {
        this.locations = locations;
    }
}
