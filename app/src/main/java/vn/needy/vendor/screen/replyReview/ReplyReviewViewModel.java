package vn.needy.vendor.screen.replyReview;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by minh_dai on 04/01/2018.
 */

public class ReplyReviewViewModel extends BaseObservable implements ReplyReviewContract.ViewModel {

    private String mPersonalNameComment;
    private Context mContext;
    private ReplyReviewContract.Presenter mPresenter;
    private String personalAnswer;

    public ReplyReviewViewModel(Context mContext) {
        this.mContext = mContext;
        mPersonalNameComment = "2Bra";
        personalAnswer = "Cảm ơn đã ủng hộ Shop ạ ? ";
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(ReplyReviewContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getPersonalNameComment() {
        return mPersonalNameComment;
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void onReplyViewCommentCLick() {

    }

    @Bindable
    public String getPersonalAnswer() {
        return personalAnswer;
    }
}
