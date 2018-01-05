package vn.needy.vendor.screen.userProfile;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import vn.needy.vendor.port.api.VendorApi;
import vn.needy.vendor.port.message.ResponseWrapper;
import vn.needy.vendor.repository.UserRepository;
import vn.needy.vendor.repository.local.UserDataLocal;
import vn.needy.vendor.repository.remote.user.UserDataRemote;
import vn.needy.vendor.repository.remote.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.repository.remote.user.response.UserInfoResponse;
import vn.needy.vendor.domain.User;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.port.error.SafetyError;

/**
 * Created by lion on 02/12/2017.
 */

public class UserProfilePresenter implements UserProfileContract.Presenter {

    private UserProfileContract.ViewModel mViewModel;

    private UserRepository mUserRepository;

    public UserProfilePresenter(UserProfileContract.ViewModel viewModel,
                                VendorApi vendorApi, SharedPrefsApi prefsApi) {
        mViewModel = viewModel;
        mUserRepository = new UserRepository(
                new UserDataRemote(vendorApi),
                new UserDataLocal(prefsApi)
        );
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getCoverPictures() {
        List<Banner> banners = new ArrayList<>();
        banners.add(new RemoteBanner("https://techent.tv/wp-content/uploads/2015/11/Banner.jpg"));
        banners.add(new RemoteBanner("http://www.dubaimallsgroup.com/wp-content/uploads/2016/09/web-banner-eid-al-adha-1200-px-x-400-px-Ol.jpg"));
        banners.add(new RemoteBanner("https://spark.adobe.com/images/landing/examples/fathersday-sale-etsy-banner.jpg"));

        mViewModel.setBanners(banners);
    }

    @Override
    public void getUserInfo() {
        mUserRepository.getUserInformation()
                .subscribeOn(Schedulers.io())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })
                .map(new Function<ResponseWrapper<UserInfoResponse>, User>() {
                    @Override
                    public User apply(ResponseWrapper<UserInfoResponse> response) throws Exception {
                        UserInfoResponse resp = response.getData();
                        return resp.getUser();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User userResponse) throws Exception {
                        if (userResponse != null) {
                            mViewModel.setUserInfo(userResponse);
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {

                    }
                });
    }

    @Override
    public void updateUserInformation(UpdateUserInfoRequest request) {
        mUserRepository.updateUserInformation(request)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        // Show ProgressBar
                        Log.e(getClass().getSimpleName(), "Show ProgressBar");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseWrapper>() {
                    @Override
                    public void accept(ResponseWrapper baseResponse) throws Exception {
                        Log.e(getClass().getSimpleName(), baseResponse.getMessage());
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        Log.e(getClass().getSimpleName(), error.getMessage());
                    }
                });
    }
}
