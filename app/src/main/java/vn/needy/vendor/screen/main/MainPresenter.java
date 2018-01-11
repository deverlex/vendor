package vn.needy.vendor.screen.main;

import io.reactivex.disposables.CompositeDisposable;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.NotificationRepository;
import vn.needy.vendor.repository.UserRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.local.NotificationDataLocal;
import vn.needy.vendor.repository.local.UserDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.user.UserDataRemote;

/**
 * Created by lion on 30/10/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getName();

    private final CompositeDisposable mCompositeDisposable;

    private final UserRepository mUserRepository;
    private final CompanyRepository mCompanyRepository;
    private final NotificationRepository mNotificationRepository;

    private MainContract.ViewModel mViewModel;

    public MainPresenter(MainContract.ViewModel viewModel, VendorApi vendorApi, SharedPrefsApi prefsApi) {
        mViewModel = viewModel;

        mUserRepository = new UserRepository(
                new UserDataRemote(vendorApi),
                new UserDataLocal(prefsApi)
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(vendorApi),
                new CompanyDataLocal()
        );
        mCompositeDisposable = new CompositeDisposable();

        mNotificationRepository = new NotificationRepository(new NotificationDataLocal());
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

    @Override
    public void viewAllNotification() {
        mNotificationRepository.viewAllNotification();
        mViewModel.onChangeNotification(0);
    }

    @Override
    public void getCountNotificationsNotView() {
        mViewModel.onChangeNotification(mNotificationRepository.getNotificationsNotView().size());
    }
}
