package vn.needy.vendor.screen.createProduct;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import java.util.List;

import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.model.Image;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.screen.ImageAdapter;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.utils.Constant;
import vn.needy.vendor.utils.navigator.Navigator;
import vn.needy.vendor.widget.GifSizeFilter;

import static vn.needy.vendor.screen.createProduct.CreateProductPlActivity.RC_CHOOSE_IMAGE;

/**
 * Created by lion on 28/11/2017.
 */

public class CreateProductPlViewModel extends BaseObservable implements CreateProductPlContract.ViewModel {

    private Context mContext;
    private Navigator mNavigator;
    private CreateProductPlContract.Presenter mPresenter;

    private CategoryWrapper mCategory;
    private boolean mVisibleImages;
    private ImageAdapter mImageAdapter;

    public CreateProductPlViewModel(Context context, Navigator navigator, ImageAdapter imageAdapter) {
        this.mContext = context;
        mNavigator = navigator;
        mImageAdapter = imageAdapter;
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
        int limitSelectable = 1;
        if (mImageAdapter.getItemCount() > 0) {
            limitSelectable = Constant.MAX_IMAGES_PUSH - mImageAdapter.getItemCount();
        }
        Matisse.from((CreateProductPlActivity) mContext)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF))
                .countable(true)
                .maxSelectable(limitSelectable)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        mContext.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(RC_CHOOSE_IMAGE);
    }

    @Override
    public void onSelectedListImages(List<Image> images) {
        if (images.size() > 0) {
            mVisibleImages = true;
        } else mVisibleImages = false;
        notifyPropertyChanged(BR.visibleImages);
        mImageAdapter.updateData(images);
    }

    @Bindable
    public String getCategory() {
        if (mCategory != null) {
            return mCategory.getName();
        } else {
            return "";
        }
    }

    @Bindable
    public boolean isVisibleImages() {
        return mVisibleImages;
    }

    @Bindable
    public ImageAdapter getImageAdapter() {
        return mImageAdapter;
    }
}
