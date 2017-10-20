package vn.needy.vendor.data.source.local;

import io.reactivex.ObservableEmitter;
import io.reactivex.functions.BiConsumer;
import io.realm.Realm;
import vn.needy.vendor.data.model.Company;
import vn.needy.vendor.data.source.CompanyDataSource;
import vn.needy.vendor.data.source.local.realm.RealmApi;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyLocalDataSource extends BaseLocalDataSource implements CompanyDataSource.LocalDataSource {

    public CompanyLocalDataSource(RealmApi realmApi) {
        super(realmApi);
    }

    @Override
    public void saveCompany(final Company company) {
        mRealmApi.realmTransactionAsync(new BiConsumer<ObservableEmitter<? super Object>, Realm>() {
            @Override
            public void accept(ObservableEmitter<? super Object> observableEmitter, Realm realm) throws Exception {
                try {
                    realm.insertOrUpdate(company);
                } catch (IllegalStateException e) {
                    observableEmitter.tryOnError(e);
                }
            }
        });
    }
}
