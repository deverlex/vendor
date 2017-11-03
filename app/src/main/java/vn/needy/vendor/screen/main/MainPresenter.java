package vn.needy.vendor.screen.main;

import io.reactivex.disposables.CompositeDisposable;
import vn.needy.vendor.data.source.CompanyRepository;
import vn.needy.vendor.data.source.UserRepository;
import vn.needy.vendor.data.source.local.CompanyLocalDataSource;
import vn.needy.vendor.data.source.local.UserLocalDataSource;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.remote.CompanyRemoteDataSource;
import vn.needy.vendor.data.source.remote.UserRemoteDataSource;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;

/**
 * Created by lion on 30/10/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getName();

    private final CompositeDisposable mCompositeDisposable;

    private final UserRepository mUserRepository;
    private final CompanyRepository mCompanyRepository;

    public MainPresenter(RealmApi realmApi) {
        mUserRepository = new UserRepository(
                new UserRemoteDataSource(VendorServiceClient.getInstance()),
                new UserLocalDataSource(realmApi, SharedPrefsImpl.getInstance())
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteDataSource(VendorServiceClient.getInstance()),
                new CompanyLocalDataSource(realmApi)
        );
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void loadCartList() {

    }

    @Override
    public void loadNotifications() {

    }
}
