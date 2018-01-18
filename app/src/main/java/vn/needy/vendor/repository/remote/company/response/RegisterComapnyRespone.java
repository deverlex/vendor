package vn.needy.vendor.repository.remote.company.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by truongpq on 16/01/2018.
 */

public class RegisterComapnyRespone {
    @Expose
    @SerializedName("company_id")
    private long mCompanyId;

    public long getCompanyId() {
        return mCompanyId;
    }

    public void setCompanyId(long companyId) {
        mCompanyId = companyId;
    }
}
