package vn.needy.vendor.screen.registerCompany;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 07/10/2017.
 */

interface RegisterCompanyContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onClickNext();

        void onClickPosition();
    }

    interface Presenter extends BasePresenter {
        void registerCompany(String nameCompany, String officeAddress,
                             String nameStore, String addressStore,
                             float lat, float lng);

    }
}
