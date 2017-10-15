package vn.needy.vendor.screen.registerUser;

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
import vn.needy.vendor.data.source.remote.CompanyRemoteDataSource;
import vn.needy.vendor.data.source.remote.UserRemoteDataSource;
import vn.needy.vendor.data.source.remote.api.error.BaseException;
import vn.needy.vendor.data.source.remote.api.error.SafetyError;
import vn.needy.vendor.data.source.remote.api.request.RegisterUserRequest;
import vn.needy.vendor.data.source.remote.api.response.RegisterUserResponse;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;

public class RegisterUserPresenter implements RegisterUserContract.Presenter {

    private static final String TAG = RegisterUserPresenter.class.getName();

    private final RegisterUserContract.ViewModel mViewModel;
    private final CompositeDisposable mCompositeDisposable;

    private final UserRepository mUserRepository;
    private CompanyRepository mCompanyRepository;

    public RegisterUserPresenter(RegisterUserContract.ViewModel viewModel) {
        mViewModel = viewModel;
        mUserRepository = new UserRepository(
                new UserRemoteDataSource(VendorServiceClient.getInstance()),
                new UserLocalDataSource(new RealmApi())
        );
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void registerUser(RegisterUserRequest registerUserRequest) {
        if (!validateDataInput(registerUserRequest.getPassword(),
                registerUserRequest.getFirstName(),
                registerUserRequest.getLastName())) {
            return;
        }
        Disposable disposable = mUserRepository.registerUser(registerUserRequest)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                }).doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterUserResponse>() {
                    @Override
                    public void accept(RegisterUserResponse userResponse) throws Exception {
                        mViewModel.onRegisterSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        Log.d(TAG, "onSafetyError");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public boolean validateDataInput(String passWord, String firstName, String lastName) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(firstName)) {
            isValidate = false;
            mViewModel.onInputFirstNameError(R.string.first_name_empty);
        }
        if (TextUtils.isEmpty(lastName)) {
            isValidate = false;
            mViewModel.onInputLastNameError(R.string.last_name_empty);
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
