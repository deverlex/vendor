package vn.needy.vendor.api.base;

import vn.needy.vendor.service.VendorApi;
import vn.needy.vendor.service.VendorServiceClient;

/**
 * Created by lion on 04/10/2017.
 */

public abstract class BaseRepository {
    protected VendorApi mVendorApi;

    public BaseRepository() {
        mVendorApi = VendorServiceClient.getInstance();
    }
}
