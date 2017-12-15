package vn.needy.vendor.repository.remote.store.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.model.wrapper.StoreWrapper;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreInfoResp {

    @Expose
    @SerializedName("store")
    private StoreWrapper mStore;

    public StoreWrapper getmStore() {
        return mStore;
    }

    public void setmStore(StoreWrapper mStore) {
        this.mStore = mStore;
    }
}
