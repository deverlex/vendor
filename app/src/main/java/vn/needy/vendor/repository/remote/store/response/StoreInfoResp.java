package vn.needy.vendor.repository.remote.store.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.port.wrapper.StoreWrapper;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreInfoResp {

    @Expose
    @SerializedName("store")
    private StoreWrapper mStore;

    @Expose
    @SerializedName("totalStaff")
    private int mTotalStaff;

    public StoreWrapper getStore() {
        return mStore;
    }

    public void setStore(StoreWrapper mStore) {
        this.mStore = mStore;
    }

    public int getTotalStaff() {
        return mTotalStaff;
    }

    public void setTotalStaff(int mTotalStaff) {
        this.mTotalStaff = mTotalStaff;
    }
}
