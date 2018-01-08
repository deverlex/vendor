package vn.needy.vendor.screen.review;

import android.content.Context;

/**
 * Created by lion on 18/12/2017.
 */

public class ReviewPresenter implements ReviewContract.Presenter{

    private Context mContext;
    private ReviewContract.ViewModel mViewModel;

    public ReviewPresenter(Context mContext, ReviewContract.ViewModel mViewModel) {
        this.mContext = mContext;
        this.mViewModel = mViewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
