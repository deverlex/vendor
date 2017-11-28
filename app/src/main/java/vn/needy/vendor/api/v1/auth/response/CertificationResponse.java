package vn.needy.vendor.api.v1.auth.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.api.base.BaseResponse;

/**
 * Created by lion on 15/10/2017.
 */

public class CertificationResponse extends BaseResponse {

    @Expose
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
