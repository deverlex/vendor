package vn.needy.vendor.screen.notification;

import java.util.List;

import vn.needy.vendor.domain.Notification;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 21/10/2017.
 */

public interface NotificationConstract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onSetNotifications(List<Notification> notifications);

        void onClickReadAll();
    }

    interface Presenter extends BasePresenter {
        void getNotificationsLocal();

        void onReadAll(List<Notification> notifications);

        void onReadNotification(Notification notification);
    }
}
