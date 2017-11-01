package vn.needy.vendor.screen.activeAccount;

import android.text.TextUtils;
import android.util.Log;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.needy.vendor.R;
import vn.needy.vendor.data.source.UserRepository;
import vn.needy.vendor.data.source.local.UserLocalDataSource;
import vn.needy.vendor.data.source.local.realm.RealmApi;
import vn.needy.vendor.data.source.local.sharedprf.SharedPrefsImpl;
import vn.needy.vendor.data.source.remote.UserRemoteDataSource;
import vn.needy.vendor.data.source.remote.api.error.BaseException;
import vn.needy.vendor.data.source.remote.api.error.SafetyError;
import vn.needy.vendor.data.source.remote.api.request.ActiveAccountRequest;
import vn.needy.vendor.data.source.remote.api.response.BaseResponse;
import vn.needy.vendor.data.source.remote.api.service.VendorServiceClient;

/**
 * Created by lion on 31/10/2017.
 */

public class ActiveAccountPresenter implements ActiveAccountContract.Presenter {

    private static final String TAG = ActiveAccountPresenter.class.getName();

    private ActiveAccountContract.ViewModel mViewModel;
    private UserRepository mUserRepository;
    private final CompositeDisposable mCompositeDisposable;

    public ActiveAccountPresenter(ActiveAccountContract.ViewModel viewModel, RealmApi realmApi) {
        mViewModel = viewModel;
        mUserRepository = new UserRepository(
                new UserRemoteDataSource(VendorServiceClient.getInstance()),
                new UserLocalDataSource(realmApi, SharedPrefsImpl.getInstance()));
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void activeAccount(ActiveAccountRequest request) {
        if (!validateInputData(request.getFullName(), request.getAddress()))
            return;
        Disposable disposable = mUserRepository.activeAccount(request)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) throws Exception {
                        mViewModel.onActiveSuccess();
                        Log.w(TAG, "accept");
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        Log.w(TAG, "errorrrrrrrrrrrrrrrrrrrrr");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public boolean validateInputData(String fullName, String address) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(fullName)) {
            isValidate = false;
            mViewModel.onInputFullNameError(R.string.full_name_empty);
        } else if (TextUtils.isEmpty(address)) {
            isValidate = false;
            mViewModel.onInputAddressError(R.string.home_address_empty);
        }
        return isValidate;
    }
}
