package vn.needy.vendor.repository.remote.store;

import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.StoreData;
import vn.needy.vendor.repository.remote.BaseDataRemote;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreDataRemote extends BaseDataRemote<VendorApi> implements StoreData.Remote {

    public StoreDataRemote(VendorApi api) {
        super(api);
    }
}
