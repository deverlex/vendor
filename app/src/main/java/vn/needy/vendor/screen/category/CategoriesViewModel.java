package vn.needy.vendor.screen.category;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.List;

import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.error.BaseException;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.screen.addProduct.AddProductPlActivity;
import vn.needy.vendor.screen.addProduct.AddProductPnActivity;
import vn.needy.vendor.screen.main.MainActivity;
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
            if (fromClass.equals(MainPageFragment.class.getName())) {
                // check source of category
                String source = extras.getString(CategoriesActivity.SOURCE_CATEGORY);
                if (source.equals())
            } else if (fromClass.equals(AddProductPnActivity.class.getName())) {
                // from add product pn

            } else if (fromClass.equals(AddProductPlActivity.class.getName())) {
                // from add product pl
            } else {
                // get all category from root (have: pn & pl)
            }
        } else {
            // get all category from root (have: pn & pl)
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
            mPresenter.getListSubCategories(mCategory.getCategory());
        }
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
        Bundle bundle = new Bundle();
        bundle.putParcelable(CategoriesActivity.CATEGORY, mCategory);
        mNavigator.startActivity(MainActivity.class, bundle);
        mNavigator.finishActivity();
    }

    public CategoryAdapter getCategoryAdapter() {
        return mCategoryAdapter;
    }
}
