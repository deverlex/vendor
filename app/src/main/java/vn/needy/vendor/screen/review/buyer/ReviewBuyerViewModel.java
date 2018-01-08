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

    private boolean reviewAll;
    private boolean review5Star;
    private boolean review4Star;
    private boolean review3Star;
    private boolean review2Star;
    private boolean review1Star;

    private boolean reviewTextColorAll;
    private boolean reviewTextColor5Star;
    private boolean reviewTextColor4Star;
    private boolean reviewTextColor3Star;
    private boolean reviewTextColor2Star;
    private boolean reviewTextColor1Star;

    private boolean reviewPersonal;
    private Context mContext;
    private ReviewBuyerContract.Presenter mPresenter;

    public ReviewBuyerViewModel(Context mContext) {
        this.mContext = mContext;
        reviewPersonal = true;

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
    public void onViewReviewByRatingClicked(TextView view ) {
        switch (view.getId())
        {
            case R.id.reviewAll:
                reviewAll = true;
                review5Star = false;
                review4Star = false;
                review3Star = false;
                review2Star = false;
                review1Star = false;

                reviewTextColorAll = true;
                reviewTextColor1Star = false;
                reviewTextColor2Star = false;
                reviewTextColor3Star = false;
                reviewTextColor4Star = false;
                reviewTextColor5Star = false;

                notifyPropertView();
                break;
            case R.id.review5Star:
                reviewAll = false;
                review5Star = true;
                review4Star = false;
                review3Star = false;
                review2Star = false;
                review1Star = false;

                reviewTextColorAll = false;
                reviewTextColor1Star = false;
                reviewTextColor2Star = false;
                reviewTextColor3Star = false;
                reviewTextColor4Star = false;
                reviewTextColor5Star = true;

                notifyPropertView();
                break;
            case R.id.review4Star:
                reviewAll = false;
                review5Star = false;
                review4Star = true;
                review3Star = false;
                review2Star = false;
                review1Star = false;

                reviewTextColorAll = false;
                reviewTextColor1Star = false;
                reviewTextColor2Star = false;
                reviewTextColor3Star = false;
                reviewTextColor4Star = true;
                reviewTextColor5Star = false;

                notifyPropertView();
                break;
            case R.id.review3Star:
                reviewAll = false;
                review5Star = false;
                review4Star = false;
                review3Star = true;
                review2Star = false;
                review1Star = false;

                reviewTextColorAll = false;
                reviewTextColor1Star = false;
                reviewTextColor2Star = false;
                reviewTextColor3Star = true;
                reviewTextColor4Star = false;
                reviewTextColor5Star = false;

                notifyPropertView();
                break;
            case R.id.review2Star:
                reviewAll = false;
                review5Star = false;
                review4Star = false;
                review3Star = false;
                review2Star = true;
                review1Star = false;

                reviewTextColorAll = false;
                reviewTextColor1Star = false;
                reviewTextColor2Star = true;
                reviewTextColor3Star = false;
                reviewTextColor4Star = false;
                reviewTextColor5Star = false;

                notifyPropertView();
                break;
            case R.id.review1Star:
                reviewAll = false;
                review5Star = false;
                review4Star = false;
                review3Star = false;
                review2Star = false;
                review1Star = true;

                reviewTextColorAll = false;
                reviewTextColor1Star = true;
                reviewTextColor2Star = false;
                reviewTextColor3Star = false;
                reviewTextColor4Star = false;
                reviewTextColor5Star = false;

                notifyPropertView();
                break;
        }
    }
    private void notifyPropertView(){
        notifyPropertyChanged(BR.reviewAll);
        notifyPropertyChanged(BR.review5Star);
        notifyPropertyChanged(BR.review4Star);
        notifyPropertyChanged(BR.review3Star);
        notifyPropertyChanged(BR.review2Star);
        notifyPropertyChanged(BR.review1Star);

        notifyPropertyChanged(BR.reviewTextColorAll);
        notifyPropertyChanged(BR.reviewTextColor1Star);
        notifyPropertyChanged(BR.reviewTextColor2Star);
        notifyPropertyChanged(BR.reviewTextColor3Star);
        notifyPropertyChanged(BR.reviewTextColor4Star);
        notifyPropertyChanged(BR.reviewTextColor5Star);
    }

    @Bindable
    public boolean getReviewAll() {
        return reviewAll;
    }

    @Bindable
    public boolean getReview5Star() {
        return review5Star;
    }

    @Bindable
    public boolean getReview4Star() {
        return review4Star;
    }

    @Bindable
    public boolean getReview3Star() {
        return review3Star;
    }

    @Bindable
    public boolean getReview2Star() {
        return review2Star;
    }

    @Bindable
    public boolean getReview1Star() {
        return review1Star;
    }

    @Bindable
    public boolean isReviewTextColorAll() {
        return reviewTextColorAll;
    }

    @Bindable
    public boolean isReviewTextColor5Star() {
        return reviewTextColor5Star;
    }

    @Bindable
    public boolean isReviewTextColor4Star() {
        return reviewTextColor4Star;
    }

    @Bindable
    public boolean isReviewTextColor3Star() {
        return reviewTextColor3Star;
    }

    @Bindable
    public boolean isReviewTextColor2Star() {
        return reviewTextColor2Star;
    }

    @Bindable
    public boolean isReviewTextColor1Star() {
        return reviewTextColor1Star;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {

    }
}
