package vn.needy.vendor.screen.createProduct.childProduct;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import java.util.List;

import vn.needy.vendor.BR;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.model.wrapper.ProductPnWrapper;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.screen.createProduct.CreateProductPlActivity;

/**
 * Created by lion on 08/12/2017.
 */

public class ChildProductViewModel extends BaseObservable implements ChildProductContract.ViewModel {

    private Context mContext;
    private ChildProductFragment mChildProductFragment;
    private ChildProductContract.Presenter mPresenter;

    private ChildProductAdapter mChildProductAdapter;
    private CategoryWrapper mCategory;

    public ChildProductViewModel(Context context, ChildProductFragment childProductFragment, ChildProductAdapter childProductAdapter) {
        this.mContext = context;
        mChildProductFragment = childProductFragment;
        mChildProductAdapter = childProductAdapter;
    }

    @Override
    public void onStart() {
        // Get All product
        mPresenter.getProducts();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(ChildProductContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBackClicked() {
        ((Activity)mContext).onBackPressed();
    }

    @Override
    public void onDoneClicked() {
        ((CreateProductPlActivity)mContext).onUpdateListChildProduct(mChildProductAdapter.getCheckedProducts());
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void onUpdateProducts(List<ProductPnWrapper> productPns) {
        mChildProductAdapter.setData(productPns);
    }

    @Override
    public void onChooseCategory() {
        Bundle extras = new Bundle();
        // put simple name thought bundle
        extras.putString(CategoriesActivity.FROM_CLASS, ChildProductFragment.class.getSimpleName());
        Intent intent = new Intent(mContext, CategoriesActivity.class);
        intent.putExtras(extras);
        mChildProductFragment.startActivityForResult(intent, ChildProductFragment.RC_CHOOSE_CATEGORY);
    }

    @Override
    public void updateCategory(CategoryWrapper category) {
        mCategory = category;
        notifyPropertyChanged(BR.category);
        mPresenter.getProductByCategory(category.getName());
    }

    @Bindable
    public ChildProductAdapter getChildProductAdapter() {
        return mChildProductAdapter;
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
