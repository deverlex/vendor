package vn.needy.vendor.api.v1.attrs;

import io.reactivex.Observable;
import vn.needy.vendor.api.base.BaseDataSource;
import vn.needy.vendor.api.v1.attrs.response.AttributeResponse;
import vn.needy.vendor.service.VendorApi;

/**
 * Created by lion on 06/12/2017.
 */

public class AttributeRemoteDataSource extends BaseDataSource
        implements AttributeDataSource.RemoteDataSource {

    public AttributeRemoteDataSource(VendorApi vendorApi) {
        super(vendorApi);
    }

    @Override
    public Observable<AttributeResponse> getListAttributeCategory(String category) {
        return mVendorApi.getAttributeCategory(category);
    }
}
