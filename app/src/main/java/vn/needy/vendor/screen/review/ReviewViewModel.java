package vn.needy.vendor.screen.review;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by lion on 16/12/2017.
 */

public class ReviewViewModel extends BaseObservable implements ReviewContract.ViewModel {

    private Context mContext;
    private ReviewContract.Presenter mPresenter;
    private ReviewSectionAdapter mAdapter;

    public ReviewViewModel(Context mContext, ReviewSectionAdapter adapter) {
        this.mContext = mContext;
        this.mAdapter = adapter;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(ReviewContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();
    }

    @Bindable
    public ReviewSectionAdapter getAdapter() {
        return mAdapter;
    }
}
