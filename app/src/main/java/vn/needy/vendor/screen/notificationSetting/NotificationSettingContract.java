package vn.needy.vendor.screen.notificationSetting;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 25/12/2017.
 */

interface NotificationSettingContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onBackPressed();

        void onNotificationCLick();

        void onReceiveEmailCLick();

        void onTurnOnReceiveEmail();

        void onReceiveNotificationClick();

        void onNotificationUpdateProductClick();

        void onReceiveChatNotifyClick();

        void onNotificationProductPromotionClick();

        void onNotificationFollowCLick();

        void onNotifyEmptyWarehouseClick();

        void onReceiveAppUpdateClick();

        void onReceiveUpdateOrderAndPaymentClick();

        void onReceiveInfoStoreAndCompany();

        void onReceiveNewsClick();

        void onReceivePersonalInfoClick();
    }

    interface Presenter extends BasePresenter {

    }

}
