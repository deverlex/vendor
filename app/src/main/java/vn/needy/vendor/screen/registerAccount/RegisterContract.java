package vn.needy.vendor.screen.registerAccount;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 02/10/2017.
 */

public interface RegisterContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onRegisterClick();

        void onBackLogin();
    }

    interface Presenter extends BasePresenter {
        void register(String phoneNumber, String password, String gender,
                      String firstName, String lastName, String address);

        boolean validateDataInput(String phoneNumber, String passWord, String firstName,
                                  String lastName, String address);
    }
}
