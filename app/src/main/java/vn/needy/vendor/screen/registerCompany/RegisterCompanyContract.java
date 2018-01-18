package vn.needy.vendor.screen.registerCompany;

import vn.needy.vendor.model.Place;
import vn.needy.vendor.repository.remote.company.request.RegisterCompanyRequest;
import vn.needy.vendor.repository.remote.store.request.CreateStoreRequest;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 07/10/2017.
 */

interface RegisterCompanyContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onInputCompanyError(int errorMsg);

        void onInputOfficeAddressError(int errorMsg);

        void onInputOpeningTimeComanyError(int msg);

        void onInputClosingTimeComanyError(int msg);

        void onInputOpeningTimeStoreError(int msg);

        void onInputClosingTimeStoreError(int msg);

        void onInputStoreNameError(int errorMsg);

        void onInputStoreAddressError(int errorMsg);

        void onRegisterError(int errorMsg);

        void onRegisterError(String message);

        void onRegisterSuccess();

        void onRegisterClick();

        void onCompanyPositionClick();

        void onStorePositionClick();

        void onShowProgressBar();

        void onHideProgressBar();

        void onBackPressed();

        void onClickEditCompanyAddress();

        void onClickEditStoreAddress();

        void updateCompanyAddress(Place place);

        void updateStoreAddress(Place place);

        void onClickOpeningTimeCompany();

        void onClickClosingTimeCompany();

        void onClickOpeningTimeStore();

        void onClickClosingTimeStore();
    }

    interface Presenter extends BasePresenter {

        void registerCompany(RegisterCompanyRequest companyRequest, CreateStoreRequest storeRequest);

        boolean validateDataInput(RegisterCompanyRequest request, CreateStoreRequest storeRequest);
    }
}
