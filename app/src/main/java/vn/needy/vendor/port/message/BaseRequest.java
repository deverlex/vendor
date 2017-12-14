package vn.needy.vendor.port.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.needy.vendor.model.BaseModel;

/**
 * Created by lion on 05/10/2017.
 */

public class BaseRequest<T> extends BaseModel {
    @Expose
    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
