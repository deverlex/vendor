package vn.needy.vendor.screen.businessManage;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.R;
import vn.needy.vendor.screen.companyProfile.CompanyProfileActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 10/01/2018.
 */

public class BusinessManageViewModel extends BaseObservable implements BusinessManageContract.ViewModel {

    private int mNumberProduct;
    private String mSiteUrl;
    private Context mContext;
    private BusinessManageContract.Presenter mPresenter;
    private Navigator mNavigator;

    public BusinessManageViewModel(Context mContext, Navigator navigator) {
        this.mContext = mContext;

        mNumberProduct = 1;
        mSiteUrl = "http://sales.needy.vn";

        mNavigator = navigator;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(BusinessManageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void onClickCompanyProfile() {
        mNavigator.startActivity(CompanyProfileActivity.class);
    }

    @Bindable
    public String getSiteUrl() {
        return mSiteUrl;
    }

    @Bindable
    public String getNumberProduct() {
        return String.format("%s %s", mNumberProduct, mContext.getString(R.string.business_product));
    }
}