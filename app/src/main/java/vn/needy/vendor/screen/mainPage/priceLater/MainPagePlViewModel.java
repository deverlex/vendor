package vn.needy.vendor.screen.mainPage.priceLater;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.List;

import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.port.wrapper.CategoryWrapper;
import vn.needy.vendor.port.wrapper.ProductPlWrapper;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.screen.mainPage.itemProduct.ProductPlAdapter;
import vn.needy.vendor.utils.Constant;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by truongpq on 02/01/2018.
 */

public class MainPagePlViewModel extends BaseObservable implements MainPagePlContract.ViewModel {
    private MainPagePlContract.Presenter mPresenter;
    private final Navigator mNavigator;
    private Context mContext;
    private Fragment mFragment;
    private ProductPlAdapter mProductPlAdapter;

    private CategoryWrapper mCategory;

    public MainPagePlViewModel(Context context, Fragment fragment, Navigator navigator, ProductPlAdapter productPlAdapter) {
        mContext = context;
        mFragment = fragment;
        this.mNavigator = navigator;
        mProductPlAdapter = productPlAdapter;
    }

    @Override
    public void onStart() {
        // Get All products Pl of company
        mPresenter.getProductByCategory(Constant.PRICE_LATER);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(MainPagePlContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClickCategories() {
        Intent intent = new Intent(mContext, CategoriesActivity.class);
        Bundle extras = new Bundle();
        // put simple name thought bundle
        extras.putString(CategoriesActivity.FROM_CLASS, MainPagePlFragment.class.getSimpleName());
        intent.putExtras(extras);
        mFragment.startActivityForResult(intent, MainPagePlFragment.RC_CHOOSE_CATEGORY);
    }

    @Override
    public void updateCategory(CategoryWrapper category) {
        mCategory = category;
        notifyPropertyChanged(BR.category);
        mPresenter.getProductByCategory(category.getName());
    }

    @Override
    public void onUpdateProducts(List<ProductPlWrapper> productPls) {
        mProductPlAdapter.setData(productPls);
    }

    @Bindable
    public String getCategory() {
        if (mCategory != null) {
            // mCategory.getTitle();
            return mCategory.getName();
        }
        return mContext.getString(R.string.all_category);
    }

    @Bindable
    public ProductPlAdapter getProductPlAdapter() {
        return mProductPlAdapter;
    }
}
