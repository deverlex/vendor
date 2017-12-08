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
import vn.needy.vendor.api.base.BaseResponse;
import vn.needy.vendor.api.v1.user.UserLocalDataSource;
import vn.needy.vendor.api.v1.user.UserRemoteDataSource;
import vn.needy.vendor.api.v1.user.UserRepository;
import vn.needy.vendor.api.v1.user.request.UpdateUserInfoRequest;
import vn.needy.vendor.api.v1.user.response.UserResponse;
import vn.needy.vendor.database.model.User;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.error.BaseException;
import vn.needy.vendor.error.SafetyError;
import vn.needy.vendor.service.VendorServiceClient;

/**
 * Created by lion on 02/12/2017.
 */

public class UserProfilePresenter implements UserProfileContract.Presenter {

    private UserProfileContract.ViewModel mViewModel;

    private final UserRepository mUserRepository;

    public UserProfilePresenter(UserProfileContract.ViewModel viewModel, RealmApi realmApi, SharedPrefsApi prefsApi) {
        mViewModel = viewModel;
        mUserRepository = new UserRepository(
                new UserRemoteDataSource(VendorServiceClient.getInstance()),
                new UserLocalDataSource(realmApi, prefsApi));
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
                .map(new Function<UserResponse, User>() {
                    @Override
                    public User apply(UserResponse userResponse) throws Exception {
                        return userResponse.getUser();
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
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) throws Exception {
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
