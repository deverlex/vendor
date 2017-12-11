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
    @SerializedName("totalStaff")
    private int mTotalStaff;

    public Company getCompany() {
        return mCompany;
    }

    public void setCompany(Company mCompany) {
        mCompany = mCompany;
    }

    public int getTotalStaff() {
        return mTotalStaff;
    }

    public void setTotalStaff(int totalStaff) {
        mTotalStaff = totalStaff;
    }
}
