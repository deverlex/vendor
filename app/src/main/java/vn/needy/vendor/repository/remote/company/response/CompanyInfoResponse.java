package vn.needy.vendor.repository.remote.company.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.repository.remote.company.context.CompanyContext;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyInfoResponse {

    @Expose
    @SerializedName("company")
    private CompanyContext company;

    @Expose
    @SerializedName(value = "number_employee")
    private int numberEmployee;

    @Expose
    @SerializedName(value = "number_store")
    private int numberStore;

    @Expose
    @SerializedName("images")
    private List<Long> images;

    public CompanyContext getCompany() {
        return company;
    }

    public void setCompany(CompanyContext company) {
        this.company = company;
    }

    public int getNumberEmployee() {
        return numberEmployee;
    }

    public void setNumberEmployee(int numberEmployee) {
        this.numberEmployee = numberEmployee;
    }

    public int getNumberStore() {
        return numberStore;
    }

    public void setNumberStore(int numberStore) {
        this.numberStore = numberStore;
    }

    public List<Long> getImages() {
        return images;
    }

    public void setImages(List<Long> images) {
        this.images = images;
    }
}
