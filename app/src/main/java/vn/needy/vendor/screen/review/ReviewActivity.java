package vn.needy.vendor.screen.review;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityReviewBinding;
import vn.needy.vendor.screen.BaseActivity;

/**
 * Created by lion on 18/12/2017.
 */

public class ReviewActivity  extends BaseActivity{

    private ReviewSectionAdapter mSectionAdapter;
    private ReviewContract.Presenter mPresenter;
    private ReviewContract.ViewModel mViewModel;

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {

        mSectionAdapter = new ReviewSectionAdapter(this , getSupportFragmentManager());

        mViewModel = new ReviewViewModel(this , mSectionAdapter);
        mPresenter = new ReviewPresenter(this , mViewModel);

        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();

        ActivityReviewBinding binding = DataBindingUtil.setContentView(this , R.layout.activity_review);

        binding.setViewModel((ReviewViewModel) mViewModel);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else {
            super.onBackPressed();
        }
    }
}
