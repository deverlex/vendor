package vn.needy.vendor.screen.registerCompany;

import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 07/10/2017.
 */

interface RegisterCompanyContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onInputCompanyError(int errorMsg);

        void onInputOfficeAddressError(int errorMsg);

        void onInputStoreNameError(int errorMsg);

        void onInputStoreAddressError(int errorMsg);

        void onRegisterError(int errorMsg);

        void onRegisterError(String message);

        void onRegisterSuccess();

        void onRegisterClick();

        void onPositionClick();

        void onShowProgressBar();

        void onHideProgressBar();

        void onBackPressed();
    }

    interface Presenter extends BasePresenter {

        void registerCompany(RegisterCompanyRequest request);

        boolean validateDataInput(RegisterCompanyRequest request);
    }
}
