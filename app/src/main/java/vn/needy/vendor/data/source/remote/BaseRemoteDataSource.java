package vn.needy.vendor.data.source.remote;

import vn.needy.vendor.data.source.remote.api.service.VendorApi;

/**
 * Created by lion on 04/10/2017.
 */

public abstract class BaseRemoteDataSource {
    VendorApi mVendorApi;

    public BaseRemoteDataSource(VendorApi vendorApi) {
        mVendorApi = vendorApi;
    }
}
