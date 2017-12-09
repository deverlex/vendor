package vn.needy.vendor.api.v1.company.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.database.model.Company;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyResponse extends BaseResponse {

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
