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
import vn.needy.vendor.model.Company;
import vn.needy.vendor.model.Store;
import vn.needy.vendor.model.User;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.port.message.BaseStatus;
import vn.needy.vendor.port.service.VendorServiceClient;
import vn.needy.vendor.repository.CompanyRepository;
import vn.needy.vendor.repository.StoreRepository;
import vn.needy.vendor.repository.UserRepository;
import vn.needy.vendor.repository.local.CompanyDataLocal;
import vn.needy.vendor.repository.local.StoreDataLocal;
import vn.needy.vendor.repository.local.UserDataLocal;
import vn.needy.vendor.repository.remote.company.CompanyRemoteData;
import vn.needy.vendor.repository.remote.store.StoreDataRemote;
import vn.needy.vendor.repository.remote.user.UserDataRemote;
import vn.needy.vendor.repository.remote.user.request.LoginReq;
import vn.needy.vendor.repository.remote.user.response.BusinessInfoResp;
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
    private StoreRepository mStoreRepository;

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
            .map(new Function<ResponseWrapper<LoginResp>, ResponseWrapper<LoginResp>>() {
                @Override
                public ResponseWrapper<LoginResp> apply(ResponseWrapper<LoginResp> resp) throws Exception {
                    LoginResp data = resp.getData();
                    if (data != null && data.getToken() != null) {
                        // save user info & token to database
                        User user = new User(data.getUser());
                        // save user to realm
                        mUserRepository.saveUserSync(user);
                        mUserRepository.saveTokenSync(data.getToken());
                    }
                    return resp;
                }
            })
            .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseWrapper<LoginResp>>() {
                @Override
                public void accept(ResponseWrapper<LoginResp> resp) throws Exception {
                    mViewModel.onHideProgressBar();
                    Log.d(TAG, resp.getStatus());
                    if (resp.getStatus().equals(BaseStatus.ERROR)) {
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
        updateRepositoryAfterResetApi();
        // check company & store info
        mUserRepository.getBusinessInformation()
                .subscribeOn(Schedulers.io())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mViewModel.onHideProgressBar();
                    }
                })
                .doOnError(new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onLoginError(error.getMessage());
                    }
                })
                .observeOn(Schedulers.computation())
                .map(new Function<ResponseWrapper<BusinessInfoResp>, ResponseWrapper<BusinessInfoResp>>() {
                    @Override
                    public ResponseWrapper<BusinessInfoResp> apply(ResponseWrapper<BusinessInfoResp> resp) throws Exception {
                        // save company & store response
                        BusinessInfoResp data = resp.getData();
                        if (data != null && resp.getStatus().equals(BaseStatus.OK)) {
                            mCompanyRepository.saveCompanySync(new Company(data.getCompany()));
                            // save store into realm
                            mStoreRepository.saveStoreSync(new Store(data.getStore()));
                        }
                        return resp;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper<BusinessInfoResp>>() {
                    @Override
                    public void accept(ResponseWrapper<BusinessInfoResp> resp) throws Exception {
                        if (resp.getStatus().equals(BaseStatus.OK)) {
                            mViewModel.onToMainPage();
                        } else {
                            mNavigator.showToastBottom(resp.getMessage());
                            mViewModel.onToRegisterCompany();
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mNavigator.showToastCenterText(error.getMessage());
                        mViewModel.onLoginError(error.getMessage());
                    }
                });

        // check company
//        mCompanyRepository.findOurCompany()
//            .subscribeOn(Schedulers.io())
//            .doAfterTerminate(new Action() {
//                @Override
//                public void run() throws Exception {
//                    mViewModel.onHideProgressBar();
//                }
//            }).doOnError(new SafetyError() {
//                @Override
//                public void onSafetyError(BaseException error) {
//                    mViewModel.onLoginError(error.getMessage());
//                }
//            }).observeOn(Schedulers.computation())
//            .map(new Function<CompanyResp, CompanyResp>() {
//                @Override
//                public CompanyResp apply(CompanyResp resp) throws Exception {
//                    // save company & store response
//                    if (resp.getCompany() != null) {
//                        mCompanyRepository.saveCompanySync(new Company(resp.getCompany()));
//                    }
//                    return resp;
//                }
//            })
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Consumer<CompanyResp>() {
//                @Override
//                public void accept(CompanyResp response) throws Exception {
//                    if (response.getStatus().equals("OK")) {
//                        mViewModel.onToMainPage();
//                    } else {
//                        mNavigator.showToastBottom(response.getMessage());
//                        mViewModel.onToRegisterCompany();
//                    }
//                }
//            }, new SafetyError() {
//                @Override
//                public void onSafetyError(BaseException error) {
//                    mViewModel.onLoginError(error.getMessage());
//                }
//            });
    }

    private void updateRepositoryAfterResetApi() {
        // update API
        mUserRepository = new UserRepository(
                new UserDataRemote(VendorServiceClient.getInstance()),
                new UserDataLocal(SharedPrefsImpl.getInstance())
        );
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteData(VendorServiceClient.getInstance()),
                new CompanyDataLocal()
        );
        mStoreRepository = new StoreRepository(
                new StoreDataRemote(VendorServiceClient.getInstance()),
                new StoreDataLocal()
        );
    }
}
