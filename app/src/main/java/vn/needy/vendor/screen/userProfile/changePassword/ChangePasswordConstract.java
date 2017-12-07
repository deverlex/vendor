package vn.needy.vendor.screen.userProfile.changePassword;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by truongpq on 04/12/2017.
 */

public class ChangePasswordConstract {
    interface ViewModel extends BaseViewModel<Presenter> {
        void onBackPressed();
    }

    interface Presenter extends BasePresenter {

    }
}
