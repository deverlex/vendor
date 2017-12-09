package vn.needy.vendor.screen;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import vn.needy.vendor.datasource.model.Image;

/**
 * Created by lion on 08/11/2017.
 */
public class ItemImageViewModel extends BaseObservable {

    private static final String TAG = ItemImageViewModel.class.getName();

    private final Image mImage;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemImageViewModel(Image image,
                                 BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
        mImage = image;
        mItemClickListener = listener;
    }

    @Bindable
    public String getImagePath() {
        Log.w(TAG, "getImagePath()");
        if (mImage != null) {
            return mImage.getPath();
        }
        return "";
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mImage);
    }
}
