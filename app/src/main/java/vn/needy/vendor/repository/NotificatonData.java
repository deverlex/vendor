package vn.needy.vendor.repository;

import java.util.List;

import vn.needy.vendor.domain.Notification;

/**
 * Created by truongpq on 10/01/2018.
 */

public interface NotificatonData {
    interface Local {
        void saveNotification(Notification notification);

        List<Notification> getAllNotifications();

        List<Notification> getNotificationsNotView();

        void viewAllNotification();
    }
}
