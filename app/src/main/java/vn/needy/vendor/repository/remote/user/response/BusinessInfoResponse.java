package vn.needy.vendor.repository.remote.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.port.wrapper.CompanyWrapper;
import vn.needy.vendor.port.wrapper.StoreWrapper;

/**
 * Created by lion on 13/12/2017.
 */

public class BusinessInfoResponse {

    @Expose
    @SerializedName("company")
    private CompanyWrapper mCompany;
    @Expose
    @SerializedName("store")
    private StoreWrapper mStore;

    public CompanyWrapper getCompany() {
        return mCompany;
    }

    public void setCompany(CompanyWrapper company) {
        mCompany = company;
    }

    public StoreWrapper getStore() {
        return mStore;
    }

    public void setStore(StoreWrapper store) {
        mStore = store;
    }
}
