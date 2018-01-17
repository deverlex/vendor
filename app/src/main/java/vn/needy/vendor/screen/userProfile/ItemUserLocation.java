package vn.needy.vendor.screen.userProfile;

import java.util.Observable;

import vn.needy.vendor.repository.remote.user.context.UserLocationContext;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by truongpq on 17/01/2018.
 */

public class ItemUserLocation extends Observable {
    private final UserLocationContext mUserLocation;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemUserLocation(UserLocationContext userLocation, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mUserLocation = userLocation;
        mItemClickListener = itemClickListener;
    }

    public String getTitle() {
        return mUserLocation.getTitle();
    }

    public String getDescription() {
        return mUserLocation.getDescription();
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mUserLocation);
    }
}
