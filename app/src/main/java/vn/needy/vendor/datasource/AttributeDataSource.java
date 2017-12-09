package vn.needy.vendor.datasource;


import io.reactivex.Observable;
import vn.needy.vendor.api.v1.attrs.response.AttributeResponse;

/**
 * Created by lion on 06/12/2017.
 */

public interface AttributeDataSource {

    Observable<AttributeResponse> getListAttributeCategory(String category);
}
