package vn.needy.vendor.repository.remote.store.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.port.wrapper.StoreWrapper;
import vn.needy.vendor.repository.remote.store.context.StoreContext;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreInfoResponse {

    @Expose
    @SerializedName("store")
    private StoreContext mStore;

    @Expose
    @SerializedName("number_employee")
    private int numberOfEmployee;

    @Expose
    @SerializedName("avatar")
    private Long avatar;

    @Expose
    @SerializedName("images")
    private List<Long> images;

    public StoreContext getStore() {
        return mStore;
    }

    public void setStore(StoreContext store) {
        mStore = store;
    }

    public int getNumberOfEmployee() {
        return numberOfEmployee;
    }

    public void setNumberOfEmployee(int numberOfEmployee) {
        this.numberOfEmployee = numberOfEmployee;
    }

    public Long getAvatar() {
        return avatar;
    }

    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }

    public List<Long> getImages() {
        return images;
    }

    public void setImages(List<Long> images) {
        this.images = images;
    }
}
