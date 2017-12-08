package vn.needy.vendor.screen.main;

import io.reactivex.disposables.CompositeDisposable;
import vn.needy.vendor.api.v1.company.CompanyRepository;
import vn.needy.vendor.api.v1.company.CompanyRepositoryImpl;
import vn.needy.vendor.api.v1.user.UserRepository;
import vn.needy.vendor.api.v1.user.UserRepositoryImpl;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.service.VendorServiceClient;

/**
 * Created by lion on 30/10/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getName();

    private final CompositeDisposable mCompositeDisposable;

    private final UserRepository mUserRepository;
    private final CompanyRepository mCompanyRepository;

    public MainPresenter() {
        mUserRepository = new UserRepositoryImpl(
                VendorServiceClient.getInstance(),
                SharedPrefsImpl.getInstance()
        );
        mCompanyRepository = new CompanyRepositoryImpl(
                VendorServiceClient.getInstance(),
                SharedPrefsImpl.getInstance()
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
