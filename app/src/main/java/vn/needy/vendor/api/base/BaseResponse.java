package vn.needy.vendor.api.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.database.model.BaseModel;

/**
 * Created by lion on 23/09/2017.
 */

public class BaseResponse extends BaseModel {
    @Expose
    @SerializedName("success")
    private boolean mSuccess;
    @Expose
    @SerializedName("message")
    private String mMessage;

    public BaseResponse() {
        super();
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public void setSuccess(boolean success) {
        mSuccess = success;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}