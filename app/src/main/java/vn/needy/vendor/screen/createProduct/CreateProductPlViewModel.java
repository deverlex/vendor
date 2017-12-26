package vn.needy.vendor.screen.createProduct;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import java.util.List;

import vn.needy.vendor.BR;
import vn.needy.vendor.model.Image;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 28/11/2017.
 */

public class CreateProductPlViewModel extends BaseObservable implements CreateProductPlContract.ViewModel {

    private Context mContext;
    private Navigator mNavigator;
    private CreateProductPlContract.Presenter mPresenter;

    private CategoryWrapper mCategory;

    public CreateProductPlViewModel(Context context, Navigator navigator) {
        this.mContext = context;
        mNavigator = navigator;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(CreateProductPlContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void onChooseCategory() {
        Bundle extras = new Bundle();
        // put simple name thought bundle
        extras.putString(CategoriesActivity.FROM_CLASS, CreateProductPlActivity.class.getSimpleName());
        mNavigator.startActivityForResult(CategoriesActivity.class, extras, CreateProductPlActivity.RC_CHOOSE_CATEGORY);
    }

    @Override
    public void updateCategory(CategoryWrapper category) {
        mCategory = category;
        notifyPropertyChanged(BR.category);
    }

    @Override
    public void onClickAddImage() {

    }

    @Override
    public void onSelectedListImages(List<Image> images) {

    }

    @Bindable
    public String getCategory() {
        if (mCategory != null) {
            return mCategory.getName();
        } else {
            return "";
        }
    }
}
