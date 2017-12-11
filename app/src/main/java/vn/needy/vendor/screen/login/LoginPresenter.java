package vn.needy.vendor.screen.login;

import android.text.TextUtils;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.model.User;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.UserRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.local.UserDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.user.UserDataRemote;
import vn.needy.vendor.repository.remote.user.request.LoginReq;
import vn.needy.vendor.repository.remote.user.response.CompanyResp;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;
import vn.needy.vendor.repository.remote.user.response.LoginResp;
import vn.needy.vendor.utils.Utils;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 05/10/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getName();

    private final LoginContract.ViewModel mViewModel;

    private Navigator mNavigator;
    private UserRepository mUserRepository;
    private CompanyRepository mCompanyRepository;

    LoginPresenter(LoginContract.ViewModel viewModel, Navigator navigator) {
        mViewModel = viewModel;
        mNavigator = navigator;
        mUserRepository = new UserRepository(
                new UserDataRemote(VendorServiceClient.getInstance()),
                new UserDataLocal(SharedPrefsImpl.getInstance())
        );
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void login(final String phoneNumber, final String passWord) {
        if (!validateDataInput(phoneNumber, passWord)) {
            return;
        }
        final LoginReq request = new LoginReq(phoneNumber, passWord);
        mUserRepository.login(request)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    mViewModel.onShowProgressBar();
                }
            })
            .doOnError(new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mNavigator.showToastCenterText(error.getMessage());
                }
            }).observeOn(Schedulers.computation())
            .map(new Function<LoginResp, LoginResp>() {
                @Override
                public LoginResp apply(LoginResp resp) throws Exception {
                    if (resp.getToken() != null) {
                        // save user info & token to database
                        User user = new User(resp.getUser());
                        // save user to realm
                        mUserRepository.saveUserSync(user);
                        mUserRepository.saveTokenSync(resp.getToken());
                    }
                    return resp;
                }
            })
            .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LoginResp>() {
                @Override
                public void accept(LoginResp resp) throws Exception {
                    mViewModel.onHideProgressBar();
                    Log.d(TAG, resp.getStatus());
                    if (resp.getStatus().equals("ERROR")) {
                        mViewModel.onLoginError(resp.getMessage());
                    }  else {
                        mViewModel.onLoginSuccess();
                    }
                }
            }, new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mViewModel.onHideProgressBar();
                    Log.d(TAG, error.getMessage());
                    mViewModel.onLoginError(R.string.error_credential);
                }
            });
//        String deviceToken = FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public boolean validateDataInput(String phoneNumber, String passWord) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(phoneNumber)) {
            isValidate = false;
            mViewModel.onInputPhoneNumberError(R.string.phone_number_empty);
        }
        if (!Utils.PhoneNumberUtils.isValidPhoneNumber(phoneNumber)) {
            isValidate = false;
            mViewModel.onInputPhoneNumberError(R.string.is_not_phone_number);
        }
        if (TextUtils.isEmpty(passWord)) {
            isValidate = false;
            mViewModel.onInputPasswordError(R.string.password_empty);
        }
        if (!TextUtils.isEmpty(passWord) && passWord.length() > 32) {
            isValidate = false;
            mViewModel.onInputPasswordError(R.string.password_over_limit);
        }
        if (!TextUtils.isEmpty(passWord) && passWord.length() < 8) {
            isValidate = false;
            mViewModel.onInputPasswordError(R.string.password_miss_length);
        }
        return isValidate;
    }

    @Override
    public void findCompany() {
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(VendorServiceClient.getInstance()),
                new CompanyDataLocal()
        );
        // check company
        mCompanyRepository.findOurCompany()
            .subscribeOn(Schedulers.io())
            .doAfterTerminate(new Action() {
                @Override
                public void run() throws Exception {
                    mViewModel.onHideProgressBar();
                }
            }).doOnError(new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {

                }
            }).doOnNext(new Consumer<CompanyResp>() {
                @Override
                public void accept(CompanyResp response) throws Exception {
                    // save company & store response
                    Log.w(TAG, "company_id: " + response.getCompanyId()
                            + ", store_id: " + response.getStoreId());

//                    mUserRepository.saveCompanyId(response.getCompanyId());
//                    mUserRepository.saveStoreId(response.getStoreId());
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<CompanyResp>() {
                @Override
                public void accept(CompanyResp response) throws Exception {
                    if (response.getStatus().equals("OK")) {
                        mViewModel.onToMainPage();
                    } else {
                        mNavigator.showToastBottom(response.getMessage());
                        mViewModel.onToRegisterCompany();
                    }
                }
            }, new SafetyError() {
                @Override
                public void onSafetyError(BaseException error) {
                    mViewModel.onLoginError(error.getMessage());
                }
            });
    }
}
