package vn.needy.vendor.repository.remote.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.model.wrapper.CompanyWrapper;
import vn.needy.vendor.port.message.BaseResponse;

/**
 * Created by lion on 10/12/2017.
 */

public class CompanyResp extends BaseResponse {

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
