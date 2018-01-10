package vn.needy.vendor.screen.review.buyer;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.TextView;

import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by minh_dai on 03/01/2018.
 */

public class ReviewBuyerViewModel extends BaseObservable implements ReviewBuyerContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>{


    private int mItemFocus;
    private boolean reviewPersonal;
    private Context mContext;
    private ReviewBuyerContract.Presenter mPresenter;

    public ReviewBuyerViewModel(Context mContext) {
        this.mContext = mContext;
        reviewPersonal = true;

        mItemFocus = 0;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(ReviewBuyerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public boolean isReviewPersonal() {
        return reviewPersonal;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {

    }

    @Override
    public void onViewReviewByRatingClicked(int item) {
        mItemFocus = item;
        notifyPropertyChanged(BR.itemFocus);
    }

    @Bindable
    public int getItemFocus() {
        return mItemFocus;
    }
}
