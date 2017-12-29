package vn.needy.vendor.screen.category;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.database.sharedprf.SharedPrefsKey;
import vn.needy.vendor.port.error.BaseException;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.screen.createProduct.CreateProductPnActivity;
import vn.needy.vendor.screen.mainPage.MainPageFragment;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/11/2017.
 */

public class CategoriesViewModel extends BaseObservable implements CategoriesContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private static final String TAG = CategoriesViewModel.class.getName();

    private final Context mContext;
    private final Navigator mNavigator;
    private final CategoryAdapter mCategoryAdapter;

    private CategoriesContract.Presenter mPresenter;
    private CategoryWrapper mCategory;
    private SharedPrefsApi mPrefsApi;
    private String mFromClass;

    public CategoriesViewModel(Context context, Navigator navigator, CategoryAdapter categoryAdapter,
                               SharedPrefsApi prefsApi, String fromClass) {
        mContext = context;
        mPrefsApi = prefsApi;
        mNavigator = navigator;
        mCategoryAdapter = categoryAdapter;
        mFromClass = fromClass;

        mCategoryAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {
        // check add product or getAsync product
        // previous activity will send an name class to bundle
        if (mFromClass.equals(MainPageFragment.class.getSimpleName())) {
            // check source of category
            int productType = mPrefsApi.get(SharedPrefsKey.PRODUCT_TYPE_CHOOSE, Integer.class);
            if (productType == R.id.price_now) {
                // getAsync category from pn
                Log.w(TAG, "getAsync category from pn");
                mPresenter.getCompanyCategoryPriceNow();
            } else {
                // getAsync category from pl
                Log.w(TAG, "getAsync category from pl");
                mPresenter.getCompanyCategoryPriceLater();
            }
        } else if (mFromClass.equals(CreateProductPnActivity.class.getSimpleName())) {
            // from add product pn
            Log.w(TAG, "from add product pn");
            mPresenter.getCategoryPriceNow();
        } else {
            // from add product pl
            Log.w(TAG, "from add product pl");
        }
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(CategoriesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (item instanceof CategoryWrapper) {
            mCategory = (CategoryWrapper) item;
            if (mFromClass.equals(MainPageFragment.class.getSimpleName())) {
                mPresenter.getCompanyCategories(mCategory.getName());
            } else {
                mPresenter.getCategories(mCategory.getName());
            }
        }
    }

    @Override
    public void onBackClicked() {
        // we needy getAsync previous list categories
        // if it is price_now/price_later then back to main

//        Activity activity = (Activity) mContext;
//        activity.setResult(CategoriesActivity.RC_OK);
//        activity.finish();
    }

    @Override
    public void onUpdateListCategory(List<CategoryWrapper> categories) {
        mCategoryAdapter.updateData(categories);
    }

    @Override
    public void onUpdateListCategoryError(BaseException exception) {

    }

    @Override
    public void backActivity() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(CategoriesActivity.CATEGORY, mCategory);
        intent.putExtras(bundle);
        mNavigator.finishActivity(CategoriesActivity.RC_OK, intent);
    }

    public CategoryAdapter getCategoryAdapter() {
        return mCategoryAdapter;
    }
}
