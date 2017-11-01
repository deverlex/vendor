package vn.needy.vendor.screen.activeAccount;

import vn.needy.vendor.data.source.remote.api.request.ActiveAccountRequest;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 31/10/2017.
 */

interface ActiveAccountContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onInputFullNameError(int msg);

        void onInputAddressError(int msg);

        void onActiveClick();

        void onPositionClick();

        void onActiveSuccess();

        void onActiveError(String msg);

        void onBackPressed();

        void onShowProgressBar();

        void onHideProgressBar();
    }

    interface Presenter extends BasePresenter {

        void activeAccount(ActiveAccountRequest request);

        boolean validateInputData(String fullName, String address);
    }
}
