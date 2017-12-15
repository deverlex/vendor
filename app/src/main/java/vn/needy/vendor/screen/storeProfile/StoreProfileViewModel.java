package vn.needy.vendor.screen.storeProfile;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.model.Store;

/**
 * Created by lion on 12/12/2017.
 */

public class StoreProfileViewModel extends BaseObservable implements StoreProfileContract.ViewModel {

    private Context mContext;
    private StoreProfileContract.Presenter mPresenter;

    private List<Banner> mBanners;
    private boolean mEnable;
    private int mDrawableEdit;
    private Store mStore;

    StoreProfileViewModel(Context context) {
        mContext = context;
        mDrawableEdit = R.drawable.ic_edits_white;
    }

    @Override
    public void onStart() {
        mPresenter.getCoverPictures();
        mPresenter.getStoreInfo();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(StoreProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public List<Banner> getBanners() {
        return mBanners;
    }

    @Bindable
    public int getDrawableEdit() {
        return mDrawableEdit;
    }

    @Bindable
    public Store getStore() {
        return mStore;
    }

    @Bindable
    public boolean isEnable() {
        return mEnable;
    }

    @Override
    public void setBanner(List<Banner> banners) {
        mBanners = banners;
        notifyPropertyChanged(BR.banners);
    }

    @Override
    public void onClickEdit() {
        if (mEnable) {
            // Update
        }

        mEnable = !mEnable;
        notifyPropertyChanged(BR.enable);
        mDrawableEdit = mEnable ? R.drawable.ic_check : R.drawable.ic_edits_white;
        notifyPropertyChanged(BR.drawableEdit);
    }

    @Override
    public void setStoreInfo(Store store) {
        Log.e("XXXXXXXXXXXX", "-------------------------" + store.getName());
        mStore = store;
        notifyPropertyChanged(BR.store);
    }
}
