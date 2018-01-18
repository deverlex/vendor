package vn.needy.vendor.screen.main;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 04/10/2017.
 */

public interface MainContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onChangeNotification(int count);

        void onResume();
    }

    interface Presenter extends BasePresenter {

        void loadCartList();

        void loadNotifications();

        void viewAllNotification();

        void getCountNotificationsNotView();
    }
}
