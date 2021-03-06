package vn.needy.vendor.screen.review.vendor;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by minh_dai on 03/01/2018.
 */

public interface ReviewVendorContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onViewReviewByRatingClicked(int item);

    }

    interface Presenter extends BasePresenter {

    }

}
