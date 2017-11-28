package vn.needy.vendor.data.source.remote.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.data.model.BaseModel;

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


    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}