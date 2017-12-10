package vn.needy.vendor.datasource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.model.BaseModel;

/**
 * Created by lion on 23/09/2017.
 */

public class BaseResponse extends BaseModel {
    @Expose
    @SerializedName("status")
    private String mStatus;
    @Expose
    @SerializedName("code")
    private int mCode;
    @Expose
    @SerializedName("message")
    private String mMessage;

    public BaseResponse() {
        super();
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}