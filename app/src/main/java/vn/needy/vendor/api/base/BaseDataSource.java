package vn.needy.vendor.api.base;

import vn.needy.vendor.service.VendorApi;

/**
 * Created by lion on 04/10/2017.
 */

public abstract class BaseDataSource {
    protected VendorApi mVendorApi;

    public BaseDataSource(VendorApi vendorApi) {
        mVendorApi = vendorApi;
    }
}
