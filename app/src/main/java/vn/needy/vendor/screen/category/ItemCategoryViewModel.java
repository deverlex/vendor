package vn.needy.vendor.screen.category;

import android.databinding.BaseObservable;
import android.util.Log;

import vn.needy.vendor.model.wrapper.CategoryWrapper;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 03/11/2017.
 */

public class ItemCategoryViewModel extends BaseObservable {

    private static final String TAG = ItemCategoryViewModel.class.getName();

    private final CategoryWrapper mCategory;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemCategoryViewModel(CategoryWrapper category,
                                 BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
        mCategory = category;
        mItemClickListener = listener;
    }

    public String getTitleCategory() {
        Log.w(TAG, "getTitleCategory()");
        if (mCategory != null) {
            return mCategory.getName();
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
