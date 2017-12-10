package vn.needy.vendor.datasource;

import vn.needy.vendor.api.VendorApi;
import vn.needy.vendor.service.VendorServiceClient;

/**
 * Created by lion on 04/10/2017.
 */

public abstract class BaseDataSource {

    protected VendorApi mVendorApi;

    public BaseDataSource() {
        mVendorApi = VendorServiceClient.getInstance();
    }
}
