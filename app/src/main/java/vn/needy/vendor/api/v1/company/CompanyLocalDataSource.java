package vn.needy.vendor.api.v1.company;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.BiConsumer;
import io.realm.Realm;
import vn.needy.vendor.api.base.BaseLocalDataSource;
import vn.needy.vendor.database.model.Company;
import vn.needy.vendor.database.realm.RealmApi;

/**
 * Created by lion on 07/10/2017.
 */

public class CompanyLocalDataSource extends BaseLocalDataSource implements CompanyDataSource.LocalDataSource {

    private final static String TAG = CompanyLocalDataSource.class.getName();

    public CompanyLocalDataSource(RealmApi realmApi) {
        super(realmApi);
    }

    @Override
    public Observable<Void> saveCompany(final Company company) {
        return mRealmApi.realmTransactionAsync(new BiConsumer<ObservableEmitter<? super Void>, Realm>() {
            @Override
            public void accept(ObservableEmitter<? super Void> observableEmitter, Realm realm) throws Exception {
                try {
                    realm.insertOrUpdate(company);
                    Log.w(TAG, "saveCompany()");
                } catch (IllegalStateException e) {
                    observableEmitter.tryOnError(e);
                }
            }
        });
    }

    @Override
    public Observable<Company> getCompany() {
        return mRealmApi.realmGet(new BiConsumer<ObservableEmitter<? super Company>, Realm>() {
            @Override
            public void accept(ObservableEmitter<? super Company> observableEmitter, Realm realm) throws Exception {
                Company company = realm.where(Company.class).findFirst();
                Log.w(TAG, "getCompany()? " + company.getName());
                observableEmitter.onNext(company);
            }
        });
    }
}
