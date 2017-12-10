package vn.needy.vendor.screen.mainPage;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.widget.RadioGroup;

import vn.needy.vendor.R;
import vn.needy.vendor.model.Category;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.screen.addProduct.AddProductPlActivity;
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
    private Category mCategory;

    private SharedPrefsApi mPrefsApi;

    private int mProductType;
    private boolean mIsPlChecked;

    public MainPageViewModel(Context context, Navigator navigator,
                             SharedPrefsApi prefsApi, final Category category) {
        mContext = context;
        mNavigator = navigator;
        // default of product type is pn - because UI set it is checked.
        mPrefsApi = prefsApi;
        int productType = mPrefsApi.get(SharedPrefsKey.PRODUCT_TYPE_CHOOSE, Integer.class);
        mProductType = productType > 0 ? productType : R.id.price_now;
        // save it into db
        prefsApi.put(SharedPrefsKey.PRODUCT_TYPE_CHOOSE, mProductType);

        mIsPlChecked = mProductType == R.id.price_later;

        // when getAsync category from result activity
        if (category != null) {
            mCategory = category;
            // save category to db
            prefsApi.putObject(SharedPrefsKey.CURRENT_CATEGORY, category);
        } else {
            // getAsync category from db
            mCategory = prefsApi.getObject(SharedPrefsKey.CURRENT_CATEGORY, Category.class);
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
        // save setting product type into db
        mPrefsApi.put(SharedPrefsKey.PRODUCT_TYPE_CHOOSE, mProductType);
    }

    @Override
    public void onClickAddProduct() {
        if (mProductType == R.id.price_now) {
            mNavigator.startActivity(AddProductPnActivity.class);
        } else {
            mNavigator.startActivity(AddProductPlActivity.class);
        }
    }

    @Override
    public void onClickCategories() {
        Bundle extras = new Bundle();
        // put simple name thought bundle
        extras.putString(CategoriesActivity.FROM_CLASS, MainPageFragment.class.getSimpleName());
        mNavigator.startActivityForResult(CategoriesActivity.class, extras, MainPageFragment.RC_CHOOSE_CATEGORY
        );
    }

    @Bindable
    public String getCategoryTitle() {
        if (mCategory != null) {
            return mCategory.getTitle();
        }
        return mContext.getString(R.string.all_category);
    }

    public boolean isPlChecked() {
        return mIsPlChecked;
    }

    public void setPlChecked(boolean plChecked) {
        mIsPlChecked = plChecked;
    }
}
