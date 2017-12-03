package vn.needy.vendor.screen.mainPage;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.screen.addProduct.AddProductPnActivity;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 23/10/2017.
 */

public class MainPageViewModel extends BaseObservable implements MainPageConstract.ViewModel {

    private static final String TAG = MainPageViewModel.class.getName();

    private final Context mContext;
    private final Navigator mNavigator;

    private MainPageConstract.Presenter mPresenter;
    private String mCategoryTitle;
    private String mCategory;

    private int mProductType;

    public MainPageViewModel(Context context, Navigator navigator, Category category) {
        mContext = context;
        mNavigator = navigator;
        // default of product type is pn - because UI set it is checked.
        mProductType = R.id.price_now;

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
        mProductType = id;
    }

    @Override
    public void onClickAddProduct() {
        Bundle bundle = new Bundle();
        bundle.putInt(MainPageFragment.PRODUCT_TYPE, mProductType);
        mNavigator.startActivity(AddProductPnActivity.class, bundle);
    }

    @Override
    public void onClickCategories() {
        Bundle extras = new Bundle();
        extras.putString(CategoriesActivity.FROM_CLASS, MainPageFragment.CLASS);
        extras.putInt(CategoriesActivity.SOURCE_CATEGORY, mProductType);
        mNavigator.startActivityForResult(CategoriesActivity.class, extras, MainPageFragment.REQUEST_CODE);
    }

    @Bindable
    public String getCategoryTitle() {
        return mCategoryTitle;
    }
}
