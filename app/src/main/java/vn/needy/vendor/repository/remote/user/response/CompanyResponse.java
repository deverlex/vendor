package vn.needy.vendor.repository.remote.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.port.wrapper.CompanyWrapper;

/**
 * Created by lion on 10/12/2017.
 */

public class CompanyResponse {

    @Expose
    @SerializedName("company")
    private CompanyWrapper mCompany;

    public CompanyWrapper getCompany() {
        return mCompany;
    }

    public void setCompany(CompanyWrapper company) {
        mCompany = company;
    }
}
