package vn.needy.vendor.screen.mainPage;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.R;
import vn.needy.vendor.data.model.Category;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 23/10/2017.
 */

public class MainPageViewModel extends BaseObservable implements MainPageConstract.ViewModel {

    private final Context mContext;
    private final Navigator mNavigator;

    private MainPageConstract.Presenter mPresenter;
    private String mCategoryTitle;
    private String mCategory;

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
    public void onClickCategories() {
        mNavigator.startActivity(CategoriesActivity.class);
    }

    @Bindable
    public String getCategoryTitle() {
        return mCategoryTitle;
    }
}
