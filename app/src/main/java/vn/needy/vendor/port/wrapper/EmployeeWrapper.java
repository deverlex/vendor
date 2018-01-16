package vn.needy.vendor.port.wrapper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by truongpq on 16/01/2018.
 */

public class EmployeeWrapper {
    @Expose
    @SerializedName("status")
    private int mStatus;

    @Expose
    @SerializedName("company_id")
    private long mCompanyId;

    @Expose
    @SerializedName("store_id")
    private long mStoreId;

    @Expose
    @SerializedName("support_mobile")
    private boolean mSupportMobile;

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public long getCompanyId() {
        return mCompanyId;
    }

    public void setCompanyId(long companyId) {
        mCompanyId = companyId;
    }

    public long getStoreId() {
        return mStoreId;
    }

    public void setStoreId(long storeId) {
        mStoreId = storeId;
    }

    public boolean isSupportMobile() {
        return mSupportMobile;
    }

    public void setSupportMobile(boolean supportMobile) {
        mSupportMobile = supportMobile;
    }
}
