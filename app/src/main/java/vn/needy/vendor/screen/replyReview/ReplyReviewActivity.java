package vn.needy.vendor.screen.replyReview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ActivityReplyReviewBinding;
import vn.needy.vendor.screen.BaseActivity;

/**
 * Created by minh_dai on 04/01/2018.
 */

public class ReplyReviewActivity extends BaseActivity {

    public static ReplyReviewActivity getInstance(){
        return new ReplyReviewActivity();
    }

    private ReplyReviewContract.Presenter mPresenter;
    private ReplyReviewContract.ViewModel mViewModel;


    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        mViewModel = new ReplyReviewViewModel(this);
        mPresenter = new ReplyReviewPresenter();

        mViewModel.setPresenter(mPresenter);
        mViewModel.onStart();

        ActivityReplyReviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_reply_review);
        binding.setViewModel((ReplyReviewViewModel) mViewModel);
    }
}
