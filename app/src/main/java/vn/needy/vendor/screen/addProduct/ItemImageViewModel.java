package vn.needy.vendor.screen.addProduct;

import android.databinding.BaseObservable;
import android.util.Log;

import vn.needy.vendor.database.model.Image;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

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

    public String getImagePath() {
        Log.w(TAG, "getImagePath()");
        if (mImage != null) {
            return mImage.getUrl();
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
