package vn.needy.vendor.data.source.remote.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.data.model.Company;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyResponse extends BaseResponse {

    @Expose
    @SerializedName("company")
    private Company mCompany;

    public Company getCompany() {
        return mCompany;
    }

    public void setCompany(Company mCompany) {
        this.mCompany = mCompany;
    }
}
