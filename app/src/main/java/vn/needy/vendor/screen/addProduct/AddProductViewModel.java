package vn.needy.vendor.screen.addProduct;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import java.util.List;

import vn.needy.vendor.R;
import vn.needy.vendor.data.model.Category;
import vn.needy.vendor.data.model.Image;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.widget.GifSizeFilter;

import static vn.needy.vendor.screen.addProduct.AddProductActivity.REQUEST_CODE_CHOOSE;

/**
 * Created by lion on 08/11/2017.
 */
public class AddProductViewModel extends BaseObservable implements AddProductContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private final Context mContext;

    private AddProductContract.Presenter mPresenter;

    private String name;
    private String description;
    private int quantity;
    private float price;
    private String promotion;
    private Category category;
    private List<Image> images;

    private ImageAdapter mImageAdapter;

    public AddProductViewModel(Context context, ImageAdapter imageAdapter) {
        mContext = context;
        mImageAdapter = imageAdapter;
        mImageAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(AddProductContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Bindable
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Bindable
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Bindable
    public String getCategory() {
        return category.getTitle();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public void onClickAddImage() {
        Matisse.from((AddProductActivity) mContext)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF))
                .countable(true)
                .maxSelectable(9)
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

    }

    @Override
    public void onClickAddAttribute() {

    }

    @Override
    public void onClickAddPromotion() {

    }

    @Override
    public void onSelectedListImages(List<Image> images) {
        mImageAdapter.updateData(images);
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {

    }
}
