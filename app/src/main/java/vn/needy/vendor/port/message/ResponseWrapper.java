package vn.needy.vendor.port.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.model.BaseModel;

/**
 * Created by lion on 23/09/2017.
 */

public class ResponseWrapper<T> extends BaseModel {
    @Expose
    @SerializedName("status")
    private String mStatus;
    @Expose
    @SerializedName("code")
    private int mCode;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("data")
    private T mData;
    @Expose
    @SerializedName("links")
    private List<String> mLinks;

    public ResponseWrapper() {
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

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    public List<String> getLinks() {
        return mLinks;
    }

    public void setLinks(List<String> links) {
        mLinks = links;
    }
}