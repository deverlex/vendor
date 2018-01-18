package vn.needy.vendor.repository.remote.company.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 08/10/2017.
 */

public class RegisterCompanyRequest {
    @Expose
    @SerializedName("company_name")
    private String mCompanyName;

    @Expose
    @SerializedName("address")
    private String mCompanyAddress;

    @Expose
    @SerializedName("opening_time")
    private String mOpeningTime;

    @Expose
    @SerializedName("closing_time")
    private String mClosingTime;

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String mCompanyName) {
        this.mCompanyName = mCompanyName;
    }

    public String getCompanyAddress() {
        return mCompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        mCompanyAddress = companyAddress;
    }

    public String getOpeningTime() {
        return mOpeningTime;
    }

    public void setOpeningTime(String openingTime) {
        mOpeningTime = openingTime;
    }

    public String getClosingTime() {
        return mClosingTime;
    }

    public void setClosingTime(String closingTime) {
        mClosingTime = closingTime;
    }
}
