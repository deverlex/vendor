package vn.needy.vendor.screen.replyReview;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by minh_dai on 04/01/2018.
 */

public interface ReplyReviewContract {

    interface ViewModel extends BaseViewModel<Presenter>{

        void onBackPressed();

        void onReplyViewCommentCLick();

    }

    interface Presenter extends BasePresenter{

    }
}
