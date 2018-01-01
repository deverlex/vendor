package vn.needy.vendor.repository.remote.attribute.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.port.wrapper.AttributeWrapper;

/**
 * Created by lion on 12/12/2017.
 */

public class AttributeInfoResponse {

    @SerializedName("attributes")
    @Expose
    private List<AttributeWrapper> attributes;

    public List<AttributeWrapper> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeWrapper> attributes) {
        this.attributes = attributes;
    }
}
