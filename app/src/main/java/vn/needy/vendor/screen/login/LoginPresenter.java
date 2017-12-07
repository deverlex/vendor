package vn.needy.vendor.screen.login;

import android.text.TextUtils;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.api.v1.auth.CredentialRepository;
import vn.needy.vendor.api.v1.company.CompanyRepository;
import vn.needy.vendor.api.v1.company.CompanyLocalDataSource;
import vn.needy.vendor.api.v1.auth.CredentialLocalDataSource;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.api.v1.auth.CredentialRemoteDataSource;
import vn.needy.vendor.api.v1.company.CompanyRemoteDataSource;
import vn.needy.vendor.error.BaseException;
import vn.needy.vendor.error.SafetyError;
import vn.needy.vendor.api.v1.auth.response.CertificationResponse;
import vn.needy.vendor.api.v1.company.response.CompanyResponse;
import vn.needy.vendor.service.VendorServiceClient;
import vn.needy.vendor.utils.Utils;

/**
 * Created by lion on 05/10/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getName();

    private final LoginContract.ViewModel mViewModel;
    private final CompositeDisposable mCompositeDisposable;

    private final CredentialRepository mCredentialRepository;
    private CompanyRepository mCompanyRepository;

    LoginPresenter(LoginContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mCredentialRepository = new CredentialRepository(
                new CredentialRemoteDataSource(VendorServiceClient.getInstance()),
                new CredentialLocalDataSource(SharedPrefsImpl.getInstance()));

        mCompositeDisposable = new CompositeDisposable();
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
        Disposable disposable = mCredentialRepository.login(phoneNumber, passWord)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mViewModel.onShowProgressBar();
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CertificationResponse>() {
                    @Override
                    public void accept(CertificationResponse certification) throws Exception {
                        mViewModel.onHideProgressBar();
                        String token = certification.getToken();
                        // Save token into storage
                        if (TextUtils.isEmpty(token)) {
                            mViewModel.onLoginError(certification.getMessage());
                        }  else {
                            mCredentialRepository.saveToken(token);
//                            mCredentialRepository.saveCredential(new Credential(phoneNumber, passWord));
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
        mCompositeDisposable.add(disposable);


//        String deviceToken = FirebaseInstanceId.getInstance().getToken();

//        Disposable disposable = mUserRepository.login(phoneNumber, passWord)
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        mViewModel.onShowProgressBar();
//                    }
//                }).doAfterTerminate(new Action() {
//                    @Override
//                    public void run() throws Exception {
////                        mViewModel.onHideProgressBar();
//                    }
//                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String token) throws Exception {
//                        // save user into app database
////                        mUserRepository.saveUser(user);
//
//                        mViewModel.onLoginSuccess();
//                    }
//                }, new SafetyError() {
//                    @Override
//                    public void onSafetyError(BaseException error) {
//                        mViewModel.onLoginError(error);
//                        mViewModel.onHideProgressBar();
//                    }
//                });
//        mCompositeDisposable.add(disposable);
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
    public void findCompanyReference() {
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteDataSource(VendorServiceClient.getInstance()),
                new CompanyLocalDataSource(SharedPrefsImpl.getInstance()));
        Disposable disposable = mCompanyRepository.getCompanyInformation()
                .subscribeOn(Schedulers.io())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mViewModel.onHideProgressBar();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CompanyResponse>() {
                    @Override
                    public void accept(CompanyResponse companyResponse) throws Exception {
                        if (companyResponse.getCompany() != null) {
                            mCompanyRepository.saveCompany(companyResponse.getCompany());
                            mViewModel.onRedirectToMain();
                        } else {
                            mViewModel.onRedirectToRegisterCompany();
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onLoginError(error.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
