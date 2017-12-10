package vn.needy.vendor.datasource.attribute;


import io.reactivex.Observable;
import vn.needy.vendor.datasource.attribute.response.AttributesResponse;

/**
 * Created by lion on 06/12/2017.
 */

public interface AttributeDataSource {

    Observable<AttributesResponse> getListAttributeCategory(String category);
}
