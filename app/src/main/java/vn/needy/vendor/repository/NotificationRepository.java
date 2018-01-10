package vn.needy.vendor.repository;

import android.util.Log;

import java.util.List;

import vn.needy.vendor.domain.Notification;

/**
 * Created by truongpq on 10/01/2018.
 */

public class NotificationRepository {
    private static final String TAG = NotificationRepository.class.getName();
    private NotificatonData.Local mLocal;

    public NotificationRepository(NotificatonData.Local local) {
        this.mLocal = local;
    }

    public void saveNotificationSync(Notification notification) {
        mLocal.saveNotification(notification);
        Log.e(TAG, "SAVE NOTIFICATION: " + notification.getTitle());
    }

    public List<Notification> getAllNotifications() {
        return mLocal.getAllNotifications();
    }

    public List<Notification> getNotificationsNotView() {
        return mLocal.getNotificationsNotView();
    }

    public void viewAllNotification() {
        mLocal.viewAllNotification();
    }
}
