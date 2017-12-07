package vn.needy.vendor.screen.companyProfile;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.util.List;

import ss.com.bannerslider.banners.Banner;
import vn.needy.vendor.R;
import vn.needy.vendor.database.model.Company;

/**
 * Created by truongpq on 04/12/2017.
 */

public class CompanyViewModel extends BaseObservable implements CompanyContract.ViewModel{
    private CompanyContract.Presenter mPresenter;

    private List<Banner> mBanners;
    private boolean mEnable;
    private int mDrawableEdit;

    public CompanyViewModel() {
        mDrawableEdit = R.drawable.ic_edits_white;
    }

    @Override
    public void onStart() {
        mPresenter.getCoverPictures();
        mPresenter.getCompanyInfo();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(CompanyContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setBanners(List<Banner> banners) {
        mBanners = banners;
        notifyPropertyChanged(BR.banners);
    }

    @Override
    public void onClickEdit() {
        mEnable = !mEnable;
        notifyPropertyChanged(BR.enable);
        mDrawableEdit = mEnable ? R.drawable.ic_check : R.drawable.ic_edits_white;
        notifyPropertyChanged(BR.drawableEdit);

    }

    @Override
    public void setCompanyInfo(Company company) {

    }

    @Bindable
    public List<Banner> getBanners() {
        return mBanners;
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
