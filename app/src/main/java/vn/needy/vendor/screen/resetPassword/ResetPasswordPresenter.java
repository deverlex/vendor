package vn.needy.vendor.screen.resetPassword;

import vn.needy.vendor.data.source.UserRepository;
import vn.needy.vendor.data.source.local.UserLocalDataSource;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.remote.UserRemoteDataSource;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;

/**
 * Created by lion on 17/10/2017.
 */

public class ResetPasswordPresenter implements ResetPasswordContract.Presenter {

    private final ResetPasswordContract.ViewModel mViewModel;
    private UserRepository mUserRepository;

    public ResetPasswordPresenter(ResetPasswordContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mUserRepository = new UserRepository(new UserRemoteDataSource(VendorServiceClient.getInstance()),
                new UserLocalDataSource(new RealmApi(), SharedPrefsImpl.getInstance()));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }


    @Override
    public void resetPassword(String phoneNumber, String firebaseToken, String password) {

    }

    @Override
    public void validateDataInput(String password) {

    }
}
