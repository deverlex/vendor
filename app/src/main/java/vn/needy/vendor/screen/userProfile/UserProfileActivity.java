package vn.needy.vendor.screen.userProfile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityUserProfileBinding;
import vn.needy.vendor.screen.BaseActivity;

/**
 * Created by lion on 09/11/2017.
 */

public class UserProfileActivity extends BaseActivity{
    private UserProfileContract.ViewModel mViewModel;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        super.onCreateActivity(savedInstanceState);
        mViewModel = new UserProfileViewModel();

        List<Banner> banners = new ArrayList<>();
        banners.add(new RemoteBanner("https://techent.tv/wp-content/uploads/2015/11/Banner.jpg"));
        banners.add(new RemoteBanner("https://techent.tv/wp-content/uploads/2015/11/Banner.jpg"));
        banners.add(new RemoteBanner("https://techent.tv/wp-content/uploads/2015/11/Banner.jpg"));
        ((UserProfileViewModel) mViewModel).setBanners(banners);

        ActivityUserProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile);
        binding.setViewModel((UserProfileViewModel) mViewModel);
    }
}
