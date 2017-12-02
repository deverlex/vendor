package vn.needy.vendor.screen.userProfile;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 09/11/2017.
 */

public class UserProfileContract {
    interface ViewModel extends BaseViewModel<Presenter> {
        void onClickEdit();
    }

    interface Presenter extends BasePresenter {

    }
}
