package vn.needy.vendor.repository.remote.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.port.wrapper.EmployeeWrapper;

/**
 * Created by truongpq on 16/01/2018.
 */

public class CheckOwnCompanyExistRespone {
    @Expose
    @SerializedName("employee")
    private EmployeeWrapper mEmployee;

    public EmployeeWrapper getEmployee() {
        return mEmployee;
    }

    public void setEmployee(EmployeeWrapper employee) {
        mEmployee = employee;
    }
}
