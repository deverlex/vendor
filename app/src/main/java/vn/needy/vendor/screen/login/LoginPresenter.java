package vn.needy.vendor.screen.login;

import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.internal.lo;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.data.model.User;
import vn.needy.vendor.data.source.UserRepository;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsKey;
import vn.needy.vendor.data.source.remote.api.error.BaseException;
import vn.needy.vendor.data.source.remote.api.error.SafetyError;
import vn.needy.vendor.data.source.remote.api.response.LoginResponse;

/**
 * Created by lion on 05/10/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getName();

    private final LoginContract.ViewModel mViewModel;
    private final CompositeDisposable mCompositeDisposable;
    private final UserRepository mUserRepository;

    LoginPresenter(LoginContract.ViewModel viewModel, UserRepository repository) {
        mViewModel = viewModel;
        mUserRepository = repository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void login(String phoneNumber, String passWord) {
        String deviceToken = FirebaseInstanceId.getInstance().getToken();
        if (!validateDataInput(phoneNumber, passWord)) {
            return;
        }
        Disposable disposable = mUserRepository.login(phoneNumber, passWord, deviceToken)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mViewModel.onShowProgressBar();
                    }
                }).doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mViewModel.onHideProgressBar();
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        SharedPrefsApi prefsApi = SharedPrefsImpl.getInstance();
                        String auth = new Gson().toJson(loginResponse.getAuth());
                        prefsApi.put(SharedPrefsKey.KEY_AUTH, auth);
                        mUserRepository.saveUser(loginResponse.getUser());
                        mViewModel.onLoginSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onLoginError(error);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public boolean validateDataInput(String phoneNumber, String passWord) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(phoneNumber)) {
            isValidate = false;
            mViewModel.onInputPhoneNumberError();
        }
        if (TextUtils.isEmpty(passWord)) {
            isValidate = false;
            mViewModel.onInputPasswordError();
        }
        return isValidate;
    }
}
