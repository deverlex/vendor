package vn.needy.vendor.repository.remote.company.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.model.Company;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyInfoResponse extends BaseResponse {

    @Expose
    @SerializedName("company")
    private Company mCompany;

    @Expose
    @SerializedName("staffCount")
    private int mStaffCount;

    public Company getCompany() {
        return mCompany;
    }

    public void setCompany(Company mCompany) {
        this.mCompany = mCompany;
    }

    public int getStaffCount() {
        return mStaffCount;
    }

    public void setStaffCount(int staffCount) {
        this.mStaffCount = staffCount;
    }
}
