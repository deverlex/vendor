package vn.needy.vendor.screen.review;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by minh_dai on 05/01/2018.
 */

public class ItemReviewRecyclerView extends BaseObservable {

    private String personalName;
    private String personalReply;
    private String cargoDate;
    private Context mContext;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemReviewRecyclerView(Context mContext, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener) {
        this.mContext = mContext;
        this.mItemClickListener = mItemClickListener;
    }

    public void onPersonalCommentClick(){
    }

    @Bindable
    public String getPersonalName() {
        return personalName;
    }

    @Bindable
    public String getPersonalReply() {
        return personalReply;
    }

    @Bindable
    public String getCargoDate() {
        return cargoDate;
    }
}
