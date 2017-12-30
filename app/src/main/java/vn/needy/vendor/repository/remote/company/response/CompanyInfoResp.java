package vn.needy.vendor.repository.remote.company.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.port.wrapper.CompanyWrapper;
import vn.needy.vendor.port.wrapper.FeeTransportWrapper;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyInfoResp {

    @Expose
    @SerializedName("company")
    private CompanyWrapper mCompany;

    @Expose
    @SerializedName("totalStaff")
    private int mTotalStaff;

    @Expose
    @SerializedName("feeTransport")
    private List<FeeTransportWrapper> mFeeTransports;

    public CompanyWrapper getCompany() {
        return mCompany;
    }

    public void setCompany(CompanyWrapper mCompany) {
        mCompany = mCompany;
    }

    public int getTotalStaff() {
        return mTotalStaff;
    }

    public void setTotalStaff(int totalStaff) {
        mTotalStaff = totalStaff;
    }

    public List<FeeTransportWrapper> getFeeTransports() {
        return mFeeTransports;
    }

    public void setFeeTransports(List<FeeTransportWrapper> feeTransports) {
        mFeeTransports = feeTransports;
    }
}
