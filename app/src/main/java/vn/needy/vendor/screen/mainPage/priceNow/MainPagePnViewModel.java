package vn.needy.vendor.screen.mainPage.priceNow;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.port.wrapper.CategoryWrapper;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by truongpq on 02/01/2018.
 */

public class MainPagePnViewModel extends BaseObservable implements MainPagePnContract.ViewModel {
    private MainPagePnContract.Presenter mPresenter;
    private final Navigator mNavigator;
    private Context mContext;
    private Fragment mFragment;

    private CategoryWrapper mCategory;

    public MainPagePnViewModel(Context context, Fragment fragment, Navigator navigator) {
        mContext = context;
        mFragment = fragment;
        this.mNavigator = navigator;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(MainPagePnContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClickCategories() {
        Intent intent = new Intent(mContext, CategoriesActivity.class);
        Bundle extras = new Bundle();
        // put simple name thought bundle
        extras.putString(CategoriesActivity.FROM_CLASS, MainPagePnFragment.class.getSimpleName());
        intent.putExtras(extras);
        mFragment.startActivityForResult(intent, MainPagePnFragment.RC_CHOOSE_CATEGORY);
    }

    @Override
    public void updateCategory(CategoryWrapper category) {
        mCategory = category;
        notifyPropertyChanged(BR.category);
    }

    @Bindable
    public String getCategory() {
        if (mCategory != null) {
            // mCategory.getTitle();
            return mCategory.getName();
        }
        return mContext.getString(R.string.all_category);
    }
}
