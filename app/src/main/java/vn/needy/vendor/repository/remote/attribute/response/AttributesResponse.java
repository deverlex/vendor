package vn.needy.vendor.repository.remote.attribute.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.port.message.BaseResponse;
import vn.needy.vendor.model.Attribute;

/**
 * Created by lion on 06/12/2017.
 */

public class AttributesResponse extends BaseResponse {

    @Expose
    @SerializedName("attributes")
    private List<Attribute> attrs;

    public List<Attribute> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Attribute> attrs) {
        this.attrs = attrs;
    }
}
