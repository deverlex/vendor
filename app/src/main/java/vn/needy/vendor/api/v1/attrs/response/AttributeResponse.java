package vn.needy.vendor.api.v1.attrs.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.database.model.Attribute;

/**
 * Created by lion on 06/12/2017.
 */

public class AttributeResponse extends BaseResponse {

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
