package vn.needy.vendor.datasource.impl;

import io.reactivex.Observable;
import vn.needy.vendor.api.base.BaseRepository;
import vn.needy.vendor.api.v1.attrs.response.AttributeResponse;
import vn.needy.vendor.datasource.AttributeDataSource;

/**
 * Created by lion on 06/12/2017.
 */

public class AttributeDataSourceImpl extends BaseRepository implements AttributeDataSource {


    public AttributeDataSourceImpl() {
        super();
    }

    @Override
    public Observable<AttributeResponse> getListAttributeCategory(String category) {
        return mVendorApi.getAttributeCategory(category);
    }
}
