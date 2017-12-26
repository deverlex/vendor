package vn.needy.vendor.screen.createProduct.childProduct;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.CheckBox;

import vn.needy.vendor.BR;
import vn.needy.vendor.model.ProductPn;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 08/12/2017.
 */

public class ItemChildProductViewModel extends BaseObservable {
    private final ProductPn mProductPn;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;
    private final OnCheckClickListener mOnCheckClickListener;
    private boolean isChecked;

    public ItemChildProductViewModel(ProductPn productPn,
                                     BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener,
                                     boolean isChecked,
                                     OnCheckClickListener onCheckClickListener) {
        this.mProductPn = productPn;
        this.mItemClickListener = itemClickListener;
        this.isChecked = isChecked;
        this.mOnCheckClickListener = onCheckClickListener;
    }

    @Bindable
    public boolean getIsChecked() {
        return isChecked;
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mProductPn);
    }

    public void onChecked(CheckBox checkBox) {
        if (mOnCheckClickListener != null) {
            mOnCheckClickListener.onCheckClicked(checkBox.isChecked(), mProductPn);
        }

        isChecked = checkBox.isChecked();
        notifyPropertyChanged(BR.isChecked);
    }

    interface OnCheckClickListener {
        void onCheckClicked(boolean isChecked, ProductPn productPn);
    }
}
