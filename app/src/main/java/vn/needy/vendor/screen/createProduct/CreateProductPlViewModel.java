package vn.needy.vendor.screen.createProduct;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import java.util.ArrayList;
import java.util.List;

import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.model.FeeTransport;
import vn.needy.vendor.model.Image;
import vn.needy.vendor.model.ProductPn;
import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.model.wrapper.FeeTransportWrapper;
import vn.needy.vendor.model.wrapper.ProductPnWrapper;
import vn.needy.vendor.repository.remote.product.request.AddProductPlReq;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.screen.ImageAdapter;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.screen.createProduct.childProduct.ChildProductFragment;
import vn.needy.vendor.utils.Constant;
import vn.needy.vendor.utils.navigator.Navigator;
import vn.needy.vendor.widget.GifSizeFilter;

import static vn.needy.vendor.screen.createProduct.CreateProductPlActivity.RC_CHOOSE_IMAGE;

/**
 * Created by lion on 28/11/2017.
 */

public class CreateProductPlViewModel extends BaseObservable implements CreateProductPlContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>{

    private Context mContext;
    private Navigator mNavigator;
    private CreateProductPlContract.Presenter mPresenter;

    private CategoryWrapper mCategory;
    private boolean mVisibleImages;
    private ImageAdapter mImageAdapter;
    private ChildProductPlAdapter mChildProductPlAdapter;
    private FeeTransportPnAdapter mFeeTransportAdapter;

    private String mName;
    private String mDescription;

    public CreateProductPlViewModel(Context context, Navigator navigator,
                                    ImageAdapter imageAdapter,
                                    ChildProductPlAdapter childProductPlAdapter,
                                    FeeTransportPnAdapter feeTransportAdapter) {
        this.mContext = context;
        mNavigator = navigator;
        mImageAdapter = imageAdapter;
        this.mChildProductPlAdapter = childProductPlAdapter;
        mFeeTransportAdapter = feeTransportAdapter;
        mFeeTransportAdapter.setItemClickListener(this);
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

    @Override
    public void onClickAddChildProduct() {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ChildProductFragment.PRODUCT_LIST, (ArrayList<? extends Parcelable>) mChildProductPlAdapter.getData());
        ((CreateProductPlActivity) mContext)
                .initFragment(android.R.id.content, ChildProductFragment.newInstance(), args);
    }

    @Override
    public void onUpdateListChildProduct(List<ProductPnWrapper> productPns) {
        mChildProductPlAdapter.notifyDataSetChanged();
    }

    @Override
    public void addFeeTransport() {
        mFeeTransportAdapter.addItem();
    }

    @Override
    public void onClickCreate() {
        AddProductPlReq request = new AddProductPlReq();
        request.setName(mName);
        request.setDescription(mDescription);
        if (mCategory != null) {
            request.setCategory(mCategory.getName());
        }

        List<FeeTransportWrapper> feeTransportWrappers = new ArrayList<>();
        for (FeeTransport ft : mFeeTransportAdapter.getData()) {
            if (ft.getFrom() == 0f && ft.getTo() == 0f && ft.getFee() == 0f) {
                continue;
            }

            feeTransportWrappers.add(new FeeTransportWrapper(ft));
        }
        request.setFeeTransport(feeTransportWrappers);

        List<Long> products = new ArrayList<>();
        for (ProductPnWrapper p : mChildProductPlAdapter.getData()) {
            products.add(p.getId());
        }
        request.setProducts(products);

        mPresenter.uploadProduct(request, mImageAdapter.getImages());
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

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    @Bindable
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    @Bindable
    public ChildProductPlAdapter getChildProductPlAdapter() {
        return mChildProductPlAdapter;
    }

    public FeeTransportPnAdapter getFeeTransportAdapter() {
        return mFeeTransportAdapter;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (item instanceof FeeTransport) {
            mFeeTransportAdapter.removeItem((FeeTransport) item);
        }
    }
}
