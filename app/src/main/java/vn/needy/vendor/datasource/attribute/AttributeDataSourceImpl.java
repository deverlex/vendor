package vn.needy.vendor.datasource.attribute;

import io.reactivex.Observable;
import vn.needy.vendor.datasource.BaseDataSource;
import vn.needy.vendor.datasource.attribute.response.AttributesResponse;

/**
 * Created by lion on 06/12/2017.
 */

public class AttributeDataSourceImpl extends BaseDataSource implements AttributeDataSource {


    public AttributeDataSourceImpl() {
        super();
    }

    @Override
    public Observable<AttributesResponse> getListAttributeCategory(String category) {
        return mVendorApi.getAttributeCategory(category);
    }
}
