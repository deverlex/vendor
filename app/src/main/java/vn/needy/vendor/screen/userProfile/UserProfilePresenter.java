package vn.needy.vendor.screen.userProfile;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import vn.needy.vendor.api.v1.user.UserLocalDataSource;
import vn.needy.vendor.api.v1.user.UserRemoteDataSource;
import vn.needy.vendor.api.v1.user.UserRepository;
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
        banners.add(new RemoteBanner("https://techent.tv/wp-content/uploads/2015/11/Banner.jpg"));
        banners.add(new RemoteBanner("https://techent.tv/wp-content/uploads/2015/11/Banner.jpg"));

        mViewModel.setBanners(banners);
    }

    @Override
    public void getUserInfo() {
        mUserRepository.getUserInformation()
                .subscribeOn(Schedulers.io())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        User user = mUserRepository.getUser();
                        if (user != null) {
                            mViewModel.setUserInfo(user);
                        }
                    }
                })
                .map(new Function<UserResponse, User>() {
                    @Override
                    public User apply(UserResponse userResponse) throws Exception {
                        mUserRepository.saveUser(userResponse.getUser());
                        return userResponse.getUser();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User userResponse) throws Exception {
                        User user = mUserRepository.getUser();
                        if (user != null) {
                            mViewModel.setUserInfo(user);
                        }
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        User user = mUserRepository.getUser();
                        if (user != null) {
                            mViewModel.setUserInfo(user);
                        }
                    }
                });
    }
}
