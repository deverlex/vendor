package vn.needy.vendor.screen.resetPassword;

import android.text.TextUtils;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.data.model.Company;
import vn.needy.vendor.data.source.CompanyRepository;
import vn.needy.vendor.data.source.UserRepository;
import vn.needy.vendor.data.source.local.CompanyLocalDataSource;
import vn.needy.vendor.data.source.local.UserLocalDataSource;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.remote.CompanyRemoteDataSource;
import vn.needy.vendor.data.source.remote.UserRemoteDataSource;
import vn.needy.vendor.data.source.remote.api.error.BaseException;
import vn.needy.vendor.data.source.remote.api.error.SafetyError;
import vn.needy.vendor.data.source.remote.api.request.ResetPasswordRequest;
import vn.needy.vendor.data.source.remote.api.response.CertificationResponse;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;
import vn.needy.vendor.utils.Utils;

/**
 * Created by lion on 17/10/2017.
 */

public class ResetPasswordPresenter implements ResetPasswordContract.Presenter {

    private static final String TAG = ResetPasswordPresenter.class.getName();

    private final CompositeDisposable mCompositeDisposable;
    private final ResetPasswordContract.ViewModel mViewModel;
    private CompanyRepository mCompanyRepository;

    private UserRepository mUserRepository;

    public ResetPasswordPresenter(ResetPasswordContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mUserRepository = new UserRepository(new UserRemoteDataSource(VendorServiceClient.getInstance()),
                new UserLocalDataSource(new RealmApi(), SharedPrefsImpl.getInstance()));
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void resetPassword(String phoneNumber, String firebaseToken, String password) {
        if (!validateDataInput(password)) return;
        phoneNumber = Utils.PhoneNumberUtils.formatPhoneNumber(phoneNumber);
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setFirebaseToken(firebaseToken);
        resetPasswordRequest.setPhoneNumber(phoneNumber);
        resetPasswordRequest.setPassword(password);
        Disposable disposable = mUserRepository.resetPassword(resetPasswordRequest)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mViewModel.onShowProgressBar();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CertificationResponse>() {
                    @Override
                    public void accept(CertificationResponse certification) throws Exception {
                        mUserRepository.saveToken(certification.getToken());
                        mViewModel.onResetPasswordSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onHideProgressBar();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public boolean validateDataInput(String password) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(password)) {
            isValidate = false;
            mViewModel.onInputPasswordError(R.string.password_empty);
        }
        if (!TextUtils.isEmpty(password) && password.length() > 32) {
            isValidate = false;
            mViewModel.onInputPasswordError(R.string.password_over_limit);
        }
        if (!TextUtils.isEmpty(password) && password.length() < 8) {
            isValidate = false;
            mViewModel.onInputPasswordError(R.string.password_miss_length);
        }
        return isValidate;
    }

    @Override
    public void findCompanyInherent() {
        mCompanyRepository = new CompanyRepository(
                new CompanyRemoteDataSource(VendorServiceClient.getInstance()),
                new CompanyLocalDataSource(new RealmApi()));
        Disposable disposable = mCompanyRepository.findCompanyInherent()
                .subscribeOn(Schedulers.io())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mViewModel.onHideProgressBar();
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Company>() {
                    @Override
                    public void accept(Company company) throws Exception {
                        ///save company and redirect to main
                        mCompanyRepository.saveCompany(company);
                        mViewModel.onRedirectToMain();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        // redirect to register company
                        mViewModel.onRedirectToRegisterCompany();
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
