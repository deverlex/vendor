package vn.needy.vendor.datasource.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.datasource.BaseResponse;

/**
 * Created by lion on 10/12/2017.
 */

public class BusinessIdResponse extends BaseResponse {

    @Expose
    @SerializedName("companyId")
    private String mCompanyId;

    @Expose
    @SerializedName("storeId")
    private String mStoreId;

    public String getCompanyId() {
        return mCompanyId;
    }

    public void setCompanyId(String id) {
        mCompanyId = id;
    }

    public String getStoreId() {
        return mStoreId;
    }

    public void setStoreId(String storeId) {
        mStoreId = storeId;
    }
}
