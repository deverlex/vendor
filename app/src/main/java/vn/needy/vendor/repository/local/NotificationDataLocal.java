package vn.needy.vendor.repository.local;

import java.util.List;

import io.realm.Realm;
import vn.needy.vendor.database.realm.RealmApi;
import vn.needy.vendor.domain.Notification;
import vn.needy.vendor.repository.NotificatonData;

/**
 * Created by truongpq on 10/01/2018.
 */

public class NotificationDataLocal implements NotificatonData.Local{
    @Override
    public void saveNotification(final Notification notification) {
        RealmApi.getSync().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(notification);
            }
        });
    }

    @Override
    public List<Notification> getAllNotifications() {
        return RealmApi.getSync().where(Notification.class).findAll();
    }

    @Override
    public List<Notification> getNotificationsNotView() {
        return RealmApi.getSync().where(Notification.class).equalTo("mIsViewed", false).findAll();
    }

    @Override
    public void viewAllNotification() {
        final List<Notification> notifications = RealmApi.getSync().where(Notification.class).equalTo("mIsViewed", false).findAll();
        RealmApi.getSync().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Notification n : notifications) {
                    n.setIsViewed(true);
                    realm.insertOrUpdate(n);
                }
            }
        });
    }
}
