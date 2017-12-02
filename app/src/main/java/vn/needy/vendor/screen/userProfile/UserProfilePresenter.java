package vn.needy.vendor.screen.userProfile;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;

/**
 * Created by lion on 02/12/2017.
 */

public class UserProfilePresenter implements UserProfileContract.Presenter {

    private UserProfileContract.ViewModel mViewModel;

    public UserProfilePresenter(UserProfileContract.ViewModel viewModel) {
        mViewModel = viewModel;
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
}
