package vn.needy.vendor.screen.notificationSetting;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 25/12/2017.
 */

interface NotificationSettingContract {

    interface ViewModel extends BaseViewModel<Presenter> {
        void onNotificationCLick();
        void onNotificationEmailCLick();
        void onBackPressed();
        void onNotification();
        void onNotificationSettingEmail();
        void onNotificationSetting();
        void onNotificationUpdateProductClick();
        void onNotificationChatClick();
        void onNotificationProductPromotionClick();
        void onNotificationFollowCLick();
        void onNotificationProductEndCLick();
        void onNotificationUpdateAppClick();
        void onNotificationProductClick();
        void notificationEmailApp();
        void notificationNewClick();
        void onNotificationPersonalClick();
        void onNotificationGroupClick();
    }

    interface Presenter extends BasePresenter {

    }

}
