package vn.needy.vendor.data.source.remote.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
