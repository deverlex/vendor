package vn.needy.vendor.screen.addProduct;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import java.util.List;

import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.database.model.Category;
import vn.needy.vendor.database.model.Image;
import vn.needy.vendor.api.v1.product.request.AddProductRequest;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.utils.Constant;
import vn.needy.vendor.widget.GifSizeFilter;

import static vn.needy.vendor.screen.addProduct.AddProductPnActivity.REQUEST_CODE_CHOOSE;

/**
 * Created by lion on 08/11/2017.
 */
public class AddProductPnViewModel extends BaseObservable implements AddProductPnContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private static final String TAG = AddProductPnViewModel.class.getName();
    private final Context mContext;

    private AddProductPnContract.Presenter mPresenter;

    private String mNameError;
    private String mDescriptionError;
    private String mQuantityError;
    private String mPriceError;

    private String mName;
    private String mDescription;
    private int mQuantity;
    private float mPrice;
    private String mPromotion;
    private float mFeeTransport;
    private Category mCategory;

    private ImageAdapter mImageAdapter;

    private boolean mVisibleImages;

    public AddProductPnViewModel(Context context, ImageAdapter imageAdapter) {
        mContext = context;
        mImageAdapter = imageAdapter;
        mImageAdapter.setItemClickListener(this);

        mVisibleImages = false;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(AddProductPnContract.Presenter presenter) {
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
    public void onClickAddImage() {
        int limitSelectable = 1;
        if (mImageAdapter.getItemCount() > 0) {
            limitSelectable = Constant.MAX_IMAGES_PUSH - mImageAdapter.getItemCount();
        }
        Matisse.from((AddProductPnActivity) mContext)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF))
                .countable(true)
                .maxSelectable(limitSelectable)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        mContext.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }

    @Override
    public void onClickCreate() {
        AddProductRequest request = new AddProductRequest();
        mPresenter.uploadProduct(request);

        //mPresenter.uploadImage(mImageAdapter.getImages());
//        Log.w(TAG, mName);
//        Log.w(TAG, mDescription);
//        Log.w(TAG, String.valueOf(mPrice));
    }

    @Override
    public void onClickAddAttribute() {

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
    public void onItemRecyclerViewClick(Object item) {

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
        mQuantity = Integer.parseInt(quantity);
    }

    @Bindable
    public String getPrice() {
        return String.valueOf(mPrice);
    }

    public void setPrice(String price) {
        mPrice = Float.parseFloat(price);
    }

    @Bindable
    public float getFeeTransport() {
        return mFeeTransport;
    }

    public void setFeeTransport(float feeTransport) {
        mFeeTransport = feeTransport;
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
        return mCategory.getTitle();
    }

    public void setCategory(Category category) {
        mCategory = category;
    }
}
