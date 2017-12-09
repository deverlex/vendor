package vn.needy.vendor.screen.main;

import io.reactivex.disposables.CompositeDisposable;
import vn.needy.vendor.api.v1.company.CompanyDataSource;
import vn.needy.vendor.api.v1.company.CompanyDataSourceImpl;
import vn.needy.vendor.api.v1.user.UserDataSource;
import vn.needy.vendor.api.v1.user.UserDataSourceImpl;
import vn.needy.vendor.datasource.sharedprf.SharedPrefsImpl;

/**
 * Created by lion on 30/10/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getName();

    private final CompositeDisposable mCompositeDisposable;

    private final UserDataSource mUserDataSource;
    private final CompanyDataSource mCompanyDataSource;

    public MainPresenter() {
        mUserDataSource = new UserDataSourceImpl(SharedPrefsImpl.getInstance());
        mCompanyDataSource = new CompanyDataSourceImpl();
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
