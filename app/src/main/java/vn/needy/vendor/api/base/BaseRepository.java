package vn.needy.vendor.api.base;

import vn.needy.vendor.service.VendorApi;

/**
 * Created by lion on 04/10/2017.
 */

public abstract class BaseRepository {
    protected VendorApi mVendorApi;

    public BaseRepository(VendorApi vendorApi) {
        mVendorApi = vendorApi;
    }
}
