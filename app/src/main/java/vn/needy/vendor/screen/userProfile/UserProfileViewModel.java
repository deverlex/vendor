package vn.needy.vendor.screen.userProfile;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.util.Log;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.BR;
import vn.needy.vendor.R;

/**
 * Created by lion on 09/11/2017.
 */

public class UserProfileViewModel extends BaseObservable implements UserProfileContract.ViewModel{

    private UserProfileContract.Presenter mPresenter;

    private List<Banner> mBanners;
    private boolean mEnable;
    private int mDrawableEdit;

    public UserProfileViewModel() {
        mDrawableEdit = R.drawable.ic_edits_white;

        // load cover pictures
    }

    @Override
    public void onStart() {
        mPresenter.getCoverPictures();

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(UserProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClickEdit() {
        mEnable = !mEnable;
        notifyPropertyChanged(BR.enable);

        if (mEnable) {
            mDrawableEdit = R.drawable.ic_check;
        } else {
            mDrawableEdit = R.drawable.ic_edits;
        }
        notifyPropertyChanged(BR.drawableEdit);
    }

    @Bindable
    public List<Banner> getBanners() {
        return mBanners;
    }

    @Override
    public void setBanners(List<Banner> banners) {
        mBanners = banners;
        notifyPropertyChanged(BR.banners);
    }

    @Bindable
    public boolean getEnable() {
        return mEnable;
    }

    @Bindable
    public int getDrawableEdit() {
        return mDrawableEdit;
    }

}
