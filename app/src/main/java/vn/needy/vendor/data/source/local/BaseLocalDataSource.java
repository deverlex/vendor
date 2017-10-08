package vn.needy.vendor.data.source.local;

import vn.needy.vendor.data.source.local.realm.RealmApi;

/**
 * Created by lion on 07/10/2017.
 */

public abstract class BaseLocalDataSource {

    protected RealmApi mRealmApi;

    public BaseLocalDataSource(RealmApi realmApi) {
        mRealmApi = realmApi;
    }
}
