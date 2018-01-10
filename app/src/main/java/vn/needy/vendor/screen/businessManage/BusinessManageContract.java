package vn.needy.vendor.screen.businessManage;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 10/01/2018.
 */

interface BusinessManageContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onBackPressed();
    }

    interface Presenter extends BasePresenter {

    }
}
