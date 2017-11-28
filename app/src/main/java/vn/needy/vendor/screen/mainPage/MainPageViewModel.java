package vn.needy.vendor.screen.mainPage;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.widget.RadioGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.screen.addProduct.AddProductActivity;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.utils.navigator.Navigator;
import vn.needy.vendor.screen.mainPage.MainPageFragment.ProductType;

/**
 * Created by lion on 23/10/2017.
 */

public class MainPageViewModel extends BaseObservable implements MainPageConstract.ViewModel {

    private final Context mContext;
    private final Navigator mNavigator;

    private MainPageConstract.Presenter mPresenter;
    private String mCategoryTitle;
    private String mCategory;

    private int mProductType;

    public MainPageViewModel(Context context, Navigator navigator, Category category) {
        mContext = context;
        mNavigator = navigator;
        if (category != null) {
            mCategory = category.getCategory();
            mCategoryTitle = category.getCategory();
        } else {
            mCategoryTitle = mContext.getString(R.string.all_category);
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(MainPageConstract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        if (id == R.id.price_now) {
            mProductType = ProductType.PRICE_NOW;
        } else if (id == R.id.price_later) {
            mProductType = ProductType.PRICE_LATER;
        }
    }

    @Override
    public void onClickAddProduct() {
        Bundle bundle = new Bundle();
        bundle.putInt(MainPageFragment.PRODUCT_TYPE, mProductType);
        mNavigator.startActivity(AddProductActivity.class, bundle);
    }

    @Override
    public void onClickCategories() {
        mNavigator.startActivity(CategoriesActivity.class);
    }

    @Bindable
    public String getCategoryTitle() {
        return mCategoryTitle;
    }
}
