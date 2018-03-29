package vn.needy.vendor.repository.remote.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lion on 15/10/2017.
 */

public class TokenResponse {

    @Expose
    @SerializedName("token_access")
    private String mTokenAccess;

    @Expose
    @SerializedName("refresh_token")
    private String mRefreshToken;

    @Expose
    @SerializedName("expires_in")
    private long mExpiresIn;


    public String getTokenAccess() {
        return mTokenAccess;
    }

    public void setTokenAccess(String tokenAccess) {
        mTokenAccess = tokenAccess;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
    }

    public long getExpiresIn() {
        return mExpiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        mExpiresIn = expiresIn;
    }
}
