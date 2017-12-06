package vn.needy.vendor.screen.category;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.database.model.Category;
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

    public CategoriesViewModel(Context context, Navigator navigator, CategoryAdapter categoryAdapter) {
        mContext = context;
        mNavigator = navigator;
        mCategoryAdapter = categoryAdapter;
        mCategoryAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {
        // check add product or get product
        // previous activity will send an name class to bundle
        Bundle extras = ((Activity) mContext).getIntent().getExtras();
        if (extras != null) {
            String fromClass = extras.getString(CategoriesActivity.FROM_CLASS);
            if (fromClass.equals(MainPageFragment.CLASS)) {
                // check source of category
                int productType = extras.getInt(CategoriesActivity.SOURCE_CATEGORY);
                if (productType == R.id.price_now) {
                    // get category from pn
                    Log.w(TAG, "get category from pn");
                } else {
                    // get category from pl
                    Log.w(TAG, "get category from pl");
                }
            } else if (fromClass.equals(AddProductPnActivity.CLASS)) {
                // from add product pn
                Log.w(TAG, "from add product pn");
            } else {
                // from add product pl
                Log.w(TAG, "from add product pl");
            }
        } else {
            // get all category from root (have: pn & pl)
            Log.w(TAG, "get all category from root (have: pn & pl)");
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
            mPresenter.getListSubCategories(mCategory.getName());
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
    public void onGetListCategorySuccess(List<Category> categories) {
        mCategoryAdapter.updateData(categories);
    }

    @Override
    public void onGetListCategoryError(BaseException exception) {

    }

    @Override
    public void onGetProductCompany() {
        backActivity();
    }

    @Override
    public void onBackAddProductPriceNow() {
        backActivity();
    }

    @Override
    public void onBackAddProductPriceLater() {
        backActivity();
    }

    private void backActivity() {
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
