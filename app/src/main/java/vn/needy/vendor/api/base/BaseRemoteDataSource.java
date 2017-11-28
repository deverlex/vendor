package vn.needy.vendor.api.base;

import vn.needy.vendor.service.VendorApi;

/**
 * Created by lion on 04/10/2017.
 */

public abstract class BaseRemoteDataSource {
    protected VendorApi mVendorApi;

    public BaseRemoteDataSource(VendorApi vendorApi) {
        mVendorApi = vendorApi;
    }
}
