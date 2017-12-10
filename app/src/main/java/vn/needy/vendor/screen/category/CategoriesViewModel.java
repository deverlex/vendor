package vn.needy.vendor.screen.category;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.model.Category;
import vn.needy.vendor.service.sharedprf.SharedPrefsApi;
import vn.needy.vendor.service.sharedprf.SharedPrefsKey;
import vn.needy.vendor.error.BaseException;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.screen.addProduct.AddProductPnActivity;
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
    private Category mCategory;
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
        // check add product or get product
        // previous activity will send an name class to bundle
        if (mFromClass.equals(MainPageFragment.class.getSimpleName())) {
            // check source of category
            int productType = mPrefsApi.get(SharedPrefsKey.PRODUCT_TYPE_CHOOSE, Integer.class);
            if (productType == R.id.price_now) {
                // get category from pn
                Log.w(TAG, "get category from pn");
                mPresenter.getCompanyLinkCategoryPriceNow();
            } else {
                // get category from pl
                Log.w(TAG, "get category from pl");
                mPresenter.getCompanyLinkCategoryPriceLater();
            }
        } else if (mFromClass.equals(AddProductPnActivity.class.getSimpleName())) {
            // from add product pn
            Log.w(TAG, "from add product pn");
            mPresenter.getLinkCategoryPriceNow();
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
        if (item instanceof Category) {
            mCategory = (Category) item;
            if (mFromClass.equals(MainPageFragment.class.getSimpleName())) {
                mPresenter.getCompanyLinkCategories(mCategory.getName());
            } else {
                mPresenter.getLinkCategories(mCategory.getName());
            }
        }
    }

    @Override
    public void onBackClicked() {
        // we needy get previous list categories
        // if it is price_now/price_later then back to main

//        Activity activity = (Activity) mContext;
//        activity.setResult(CategoriesActivity.RC_OK);
//        activity.finish();
    }

    @Override
    public void onUpdateListCategory(List<Category> categories) {
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
