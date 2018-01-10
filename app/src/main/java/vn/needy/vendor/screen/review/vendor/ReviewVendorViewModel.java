package vn.needy.vendor.screen.review.vendor;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.TextView;

import vn.needy.vendor.BR;
import vn.needy.vendor.R;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;
import vn.needy.vendor.screen.replyReview.ReplyReviewActivity;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by minh_dai on 03/01/2018.
 */

public class ReviewVendorViewModel extends BaseObservable implements ReviewVendorContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>{

    private Navigator mNavigator;
    private boolean reviewPersonal;
    private Context mContext;
    private ReviewVendorContract.Presenter mPresenter;

    private int mItemFocus;

    private String personalName;
    private String personalAnswer;


    public ReviewVendorViewModel(Context mContext , Navigator navigator) {
        this.mContext = mContext;
        this.mNavigator = navigator;
        personalAnswer = mContext.getString(R.string.shops_thanks);

        reviewPersonal = false;
        personalName = "2Bra";

        mItemFocus = 0;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(ReviewVendorContract.Presenter presenter) {
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

