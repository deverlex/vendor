package vn.needy.vendor.port.wrapper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 09/01/2018.
 */

public class TokenWrapper {

    @SerializedName("token_access")
    @Expose
    private String tokenAccess;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @SerializedName("expires_in")
    @Expose
    private long expiresIn;

    public String getTokenAccess() {
        return tokenAccess;
    }

    public void setTokenAccess(String tokenAccess) {
        this.tokenAccess = tokenAccess;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
