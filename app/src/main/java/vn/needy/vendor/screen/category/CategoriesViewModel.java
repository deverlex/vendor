package vn.needy.vendor.screen.category;

import android.content.Context;
import android.databinding.BaseObservable;

import vn.needy.vendor.data.model.Category;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 03/11/2017.
 */

public class CategoriesViewModel extends BaseObservable implements CategoriesContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>{

    private final Context mContext;
    private final Navigator mNavigator;
    private final CategoryAdapter mCategoryAdapter;

    private CategoriesContract.Presenter mPresenter;

    public CategoriesViewModel(Context context, Navigator navigator, CategoryAdapter categoryAdapter) {
        mContext = context;
        mNavigator = navigator;
        mCategoryAdapter = categoryAdapter;
        mCategoryAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {

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

    }
}
