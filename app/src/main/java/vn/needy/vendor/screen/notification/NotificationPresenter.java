package vn.needy.vendor.screen.notification;

import java.util.List;

import vn.needy.vendor.domain.Notification;
import vn.needy.vendor.repository.NotificationRepository;
import vn.needy.vendor.repository.local.NotificationDataLocal;

/**
 * Created by lion on 21/10/2017.
 */

public class NotificationPresenter implements NotificationConstract.Presenter{

    private NotificationConstract.ViewModel mViewModel;

    private NotificationRepository mNotificationRepository;

    public NotificationPresenter(NotificationConstract.ViewModel viewModel) {
        this.mViewModel = viewModel;
        mNotificationRepository = new NotificationRepository(new NotificationDataLocal());
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getNotificationsLocal() {
        List<Notification> notifications = mNotificationRepository.getAllNotifications();
        mViewModel.onSetNotifications(notifications);
    }

    @Override
    public void onReadAll(List<Notification> notifications) {
        mNotificationRepository.readAllNotification();
    }

    @Override
    public void onReadedNotification(Notification notification) {
        mNotificationRepository.readedNotification(notification);
    }
}
