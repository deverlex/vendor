package vn.needy.vendor.data.source.remote.api.security;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 15/10/2017.
 */

public class Certification {

    @Expose
    @SerializedName("token")
    private String token;
    @Expose
    @SerializedName("message")
    private String message;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
