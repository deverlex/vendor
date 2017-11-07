package vn.needy.vendor.screen.category;

import android.databinding.BaseObservable;
import android.util.Log;

import vn.needy.vendor.data.model.Category;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 03/11/2017.
 */

public class ItemCategoryViewModel extends BaseObservable {

    private static final String TAG = ItemCategoryViewModel.class.getName();

    private final Category mCategory;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemCategoryViewModel(Category category,
                                 BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
        mCategory = category;
        mItemClickListener = listener;
    }

    public String getTitleCategory() {
        Log.w(TAG, "getTitleCategory()");
        if (mCategory != null) {
            return mCategory.getCategory();
        }
        return "";
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mCategory);
    }
}
