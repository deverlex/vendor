package vn.needy.vendor.screen.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.needy.vendor.domain.Notification;

/**
 * Created by lion on 21/10/2017.
 */

public class NotificationPresenter implements NotificationConstract.Presenter{

    private NotificationConstract.ViewModel mViewModel;

    public NotificationPresenter(NotificationConstract.ViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onGetNotification() {
        List<Notification> notifications = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Notification notification = new Notification();
            notification.setId(i);
            notification.setSenderId("senderId");
            notification.setCreateTime("09:21 19-12-17");
            notification.setTitle("Khuyến mãi hấp dẫn");
            notification.setHtmlContent("<font color=\"red\">Mua một tặng một </font> cho tất cả các sản phẩm gas<br/><font color=\"red\">Miễn phí vẫn chuyển</font>  cho đơn hàng từ 500k trở lên");
            notification.setReferenceGUI(".screen.category.CategoriesActivity");
            notifications.add(notification);
            Random random = new Random();
            notification.setIsReaded(random.nextBoolean());
        }
        mViewModel.onSetNotifications(notifications);
    }

    @Override
    public void onReadAll(List<Notification> notifications) {
        for (Notification notification : notifications) {
            notification.setIsReaded(true);
        }
    }
}
