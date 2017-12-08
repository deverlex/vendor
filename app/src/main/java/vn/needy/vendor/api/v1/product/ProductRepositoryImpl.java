package vn.needy.vendor.api.v1.product;

import vn.needy.vendor.api.base.BaseRepository;
import vn.needy.vendor.service.VendorApi;

/**
 * Created by lion on 09/11/2017.
 */

public class ProductRepositoryImpl extends BaseRepository implements ProductRepository {

    public ProductRepositoryImpl(VendorApi vendorApi) {
        super(vendorApi);
    }
}
