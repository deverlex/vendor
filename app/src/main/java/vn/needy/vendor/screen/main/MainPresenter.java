package vn.needy.vendor.screen.main;

import io.reactivex.disposables.CompositeDisposable;
import vn.needy.vendor.datasource.company.CompanyDataSource;
import vn.needy.vendor.datasource.company.CompanyDataSourceImpl;
import vn.needy.vendor.datasource.user.UserDataSource;
import vn.needy.vendor.datasource.user.UserDataSourceImpl;
import vn.needy.vendor.service.sharedprf.SharedPrefsApi;
import vn.needy.vendor.service.sharedprf.SharedPrefsImpl;

/**
 * Created by lion on 30/10/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getName();

    private final CompositeDisposable mCompositeDisposable;

    private final UserDataSource mUserDataSource;
    private final CompanyDataSource mCompanyDataSource;

    public MainPresenter(SharedPrefsApi prefsApi) {
        mUserDataSource = new UserDataSourceImpl(prefsApi);
        mCompanyDataSource = new CompanyDataSourceImpl(prefsApi);
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
