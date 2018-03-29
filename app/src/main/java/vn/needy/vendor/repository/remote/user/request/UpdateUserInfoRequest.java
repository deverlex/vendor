package vn.needy.vendor.repository.remote.user.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.repository.remote.user.context.UpdateUserContext;
import vn.needy.vendor.repository.remote.user.context.UserLocationContext;

/**
 * Created by truongpq on 06/12/2017.
 */

public class UpdateUserInfoRequest {

    @Expose
    @SerializedName("user")
    private UpdateUserContext user;

    @Expose
    @SerializedName("user_location")
    private List<UserLocationContext> locations;

    @Expose
    @SerializedName("deleted_location")
    private List<UserLocationContext> deletedlocations;

    public UpdateUserContext getUser() {
        return user;
    }

    public void setUser(UpdateUserContext user) {
        this.user = user;
    }

    public List<UserLocationContext> getLocations() {
        return locations;
    }

    public void setLocations(List<UserLocationContext> locations) {
        this.locations = locations;
    }

    public List<UserLocationContext> getDeletedlocations() {
        return deletedlocations;
    }

    public void setDeletedlocations(List<UserLocationContext> deletedlocations) {
        this.deletedlocations = deletedlocations;
    }
}
