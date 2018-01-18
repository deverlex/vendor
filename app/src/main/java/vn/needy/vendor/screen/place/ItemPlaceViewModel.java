package vn.needy.vendor.screen.place;

import android.databinding.BaseObservable;

import vn.needy.vendor.model.Place;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by truongpq on 12/01/2018.
 */

public class ItemPlaceViewModel extends BaseObservable{
    private final Place mPlace;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Place>
            mItemClickListener;

    public ItemPlaceViewModel(Place place, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Place> itemClickListener) {
        mPlace = place;
        mItemClickListener = itemClickListener;
    }

    public String getName() {
        return mPlace.getName();
    }

    public String getAddress() {
        return mPlace.getAddress();
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mPlace);
    }
}
