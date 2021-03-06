package vn.needy.vendor.screen.createProduct;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.domain.FeeTransport;
import vn.needy.vendor.port.wrapper.AttributeWrapper;
import vn.needy.vendor.port.wrapper.CategoryWrapper;
import vn.needy.vendor.model.Image;
import vn.needy.vendor.port.wrapper.FeeTransportWrapper;
import vn.needy.vendor.repository.remote.product.request.AddProductPnRequest;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.screen.ImageAdapter;
import vn.needy.vendor.screen.createProduct.attribute.AttributeFragment;
import vn.needy.vendor.screen.category.CategoriesActivity;
import vn.needy.vendor.utils.Constant;
import vn.needy.vendor.utils.navigator.Navigator;
import vn.needy.vendor.widget.GifSizeFilter;

import static vn.needy.vendor.screen.createProduct.CreateProductPnActivity.RC_CHOOSE_IMAGE;

/**
 * Created by lion on 08/11/2017.
 */
public class CreateProductPnViewModel extends BaseObservable implements CreateProductPnContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private static final String TAG = CreateProductPnViewModel.class.getName();
    private final Context mContext;

    private CreateProductPnContract.Presenter mPresenter;
    private Navigator mNavigator;

    private String mNameError;
    private String mDescriptionError;
    private String mQuantityError;
    private String mPriceError;
    private String mCategoryError;
    private String mAttributesError;

    private String mName;
    private String mDescription;
    private int mQuantity;
    private float mPrice;
    private String mPromotion;
    private CategoryWrapper mCategory;

    private ImageAdapter mImageAdapter;
    private Map<String, Object> mAttributes;
    private AttributeProductPnAdapter mAttributeResultPnAdapter;
    private FeeTransportPnAdapter mFeeTransportPnAdapter;

    private boolean mVisibleImages;

    public CreateProductPnViewModel(Context context, Navigator navigator, ImageAdapter imageAdapter, AttributeProductPnAdapter attributeResultPnAdapter, FeeTransportPnAdapter feeTransportAdapter) {
        mContext = context;
        mNavigator = navigator;
        mAttributes = new HashMap<>();
        mAttributeResultPnAdapter = attributeResultPnAdapter;
        mImageAdapter = imageAdapter;
        mImageAdapter.setItemClickListener(this);
        mFeeTransportPnAdapter = feeTransportAdapter;
        mFeeTransportPnAdapter.setItemClickListener(this);
        mVisibleImages = false;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(CreateProductPnContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onInputNameError(int msg) {
        mNameError = mContext.getString(msg);
        notifyPropertyChanged(BR.nameError);
    }

    @Override
    public void onInputDescriptionError(int msg) {
        mDescriptionError = mContext.getString(msg);
        notifyPropertyChanged(BR.descriptionError);
    }

    @Override
    public void onInputQuantityError(int msg) {
        mQuantityError = mContext.getString(msg);
        notifyPropertyChanged(BR.quantityError);
    }

    @Override
    public void onInputPriceError(int msg) {
        mPriceError = mContext.getString(msg);
        notifyPropertyChanged(BR.priceError);
    }

    @Override
    public void onInputCategoryError(int msg) {
        mCategoryError = mContext.getString(msg);
        notifyPropertyChanged(BR.categoryError);
    }

    @Override
    public void onInputAttributesError(int msg) {
        mAttributesError = mContext.getString(msg);
        notifyPropertyChanged(BR.attributesError);
    }

    @Override
    public void onChooseCategory() {
        Bundle extras = new Bundle();
        // put simple name thought bundle
        extras.putString(CategoriesActivity.FROM_CLASS, CreateProductPnActivity.class.getSimpleName());
        mNavigator.startActivityForResult(CategoriesActivity.class, extras, CreateProductPnActivity.RC_CHOOSE_CATEGORY);
    }

    @Override
    public void onClickAddImage() {
        int limitSelectable = 1;
        if (mImageAdapter.getItemCount() > 0) {
            limitSelectable = Constant.MAX_IMAGES_PUSH - mImageAdapter.getItemCount();
        }
        Matisse.from((CreateProductPnActivity) mContext)
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
    public void onClickCreate() {
        AddProductPnRequest request = new AddProductPnRequest();
        request.setName(mName);
        request.setDescription(mDescription);
        if (mCategory != null) {
            request.setCategory(mCategory.getName());
        }
        request.setPrice(mPrice);
        request.setQuantity(mQuantity);
        request.setAttributes(mAttributes);
        List<FeeTransportWrapper> feeTransportWrappers = new ArrayList<>();
        for (FeeTransport ft : mFeeTransportPnAdapter.getData()) {
            if (ft.getFrom() == 0f && ft.getTo() == 0f && ft.getFee() == 0f) {
                continue;
            }

            feeTransportWrappers.add(new FeeTransportWrapper(ft));
        }
        request.setFeeTransport(feeTransportWrappers);
        mPresenter.uploadProduct(request, mImageAdapter.getImages());
    }

    @Override
    public void onClickAddAttribute() {
        if (mCategory != null) {
            Bundle extras = new Bundle();
            extras.putParcelable(CategoriesActivity.CATEGORY, mCategory);
            ((CreateProductPnActivity) mContext)
                    .initFragment(android.R.id.content, AttributeFragment.getInstance(), extras);
        } else {
            // notify require choose category before add attribute

        }
    }

    @Override
    public void onClickAddPromotion() {

    }

    @Override
    public void onSelectedListImages(List<Image> images) {
        Log.d(TAG, "onSelectedListImages: " + images.size());
        if (images.size() > 0) {
            mVisibleImages = true;
        } else mVisibleImages = false;
        notifyPropertyChanged(BR.visibleImages);
        mImageAdapter.updateData(images);
    }

    @Override
    public void updateCategory(CategoryWrapper category) {
        mCategory = category;
        notifyPropertyChanged(BR.category);
        mCategoryError = null;
        notifyPropertyChanged(BR.categoryError);
    }

    @Override
    public void onSelectedListAttribute(final List<AttributeWrapper> attributes) {
        // Update Attribute in result View
        mAttributeResultPnAdapter.updateData(attributes);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                for (AttributeWrapper attribute : attributes) {
                    mAttributes.put(attribute.getName(), attribute.getValue());
                }

            }
        });

        mAttributesError = null;
        notifyPropertyChanged(BR.attributesError);
    }

    @Override
    public void onBackPressed() {
        ((Activity)mContext).onBackPressed();
    }

    @Override
    public void addFeeTransport() {
        mFeeTransportPnAdapter.addItem();
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (item instanceof FeeTransport) {
            mFeeTransportPnAdapter.removeItem((FeeTransport) item);
        }
    }

    @Bindable
    public boolean isVisibleImages() {
        return mVisibleImages;
    }

    public void setVisibleImages(boolean visibleImages) {
        this.mVisibleImages = visibleImages;
    }

    public ImageAdapter getImageAdapter() {
        return mImageAdapter;
    }

    @Bindable
    public String getNameError() {
        return mNameError;
    }

    @Bindable
    public String getDescriptionError() {
        return mDescriptionError;
    }

    @Bindable
    public String getQuantityError() {
        return mQuantityError;
    }

    @Bindable
    public String getPriceError() {
        return mPriceError;
    }

    @Bindable
    public String getCategoryError() {
        return mCategoryError;
    }

    @Bindable
    public String getAttributesError() {
        return mAttributesError;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Bindable
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    @Bindable
    public String getQuantity() {
        return String.valueOf(mQuantity);
    }

    public void setQuantity(String quantity) {
        if (!TextUtils.isEmpty(quantity)) {
            mQuantity = Integer.parseInt(quantity);
        }
    }

    @Bindable
    public String getPrice() {
        return String.valueOf(mPrice);
    }

    public void setPrice(String price) {
        if (!TextUtils.isEmpty(price)) {
            mPrice = Float.parseFloat(price);
        }
    }



    @Bindable
    public String getPromotion() {
        return mPromotion;
    }

    public void setPromotion(String promotion) {
        mPromotion = promotion;
    }

    @Bindable
    public String getCategory() {
        if (mCategory != null) {
            return mCategory.getName();
        }
        return mContext.getString(R.string.choose_category);
    }

    public void setCategory(CategoryWrapper category) {
        mCategory = category;
    }

    @Bindable
    public AttributeProductPnAdapter getAttributeResultPnAdapter() {
        return mAttributeResultPnAdapter;
    }

    @Bindable
    public FeeTransportPnAdapter getFeeTransportPnAdapter() {
        return mFeeTransportPnAdapter;
    }
}
