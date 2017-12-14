package vn.needy.vendor.repository.remote.attribute.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.model.wrapper.AttributeWrapper;

/**
 * Created by lion on 06/12/2017.
 */

public class AttributesResp {

    @Expose
    @SerializedName("attributes")
    private List<AttributeWrapper> mAttributes;

    public List<AttributeWrapper> getAttributes() {
        return mAttributes;
    }

    public void setAttributes(List<AttributeWrapper> attrs) {
        mAttributes = attrs;
    }
}
