package vn.needy.vendor.screen.notificationSetting;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import vn.needy.vendor.R;

/**
 * Created by lion on 25/12/2017.
 */

public class NotificationSettingViewModel extends BaseObservable implements NotificationSettingContract.ViewModel{

    private Context mContext;
    private NotificationSettingContract.Presenter mPresenter;
    private boolean mShowNotificationSetting;
    private boolean mShowReceiveEmailSetting;
    private int mIconNotificationEmail ;
    private int mIconNotification ;
    private int mIconExpand = R.drawable.ic_expand;
    private boolean mReceiveEmail;
    private boolean mReceiveNotification;
    private boolean mNotificationUpdateProduct;
    private boolean notificationChat;
    private boolean notificationPromotion;
    private boolean notificationFollow;
    private boolean notifyEmptyWarehouse;
    private boolean receiveAppUpdate;
    private boolean receiveUpdateOrderAndPayment;
    private boolean receiveInfoStoreAndCompany;
    private boolean receiveNews;
    private boolean receivePersonalInfo;

    public NotificationSettingViewModel(Context mContext) {
        this.mContext = mContext;
        mIconNotificationEmail = R.drawable.ic_next_right;
        mIconNotification = R.drawable.ic_next_right;
        mReceiveEmail = false;
        mReceiveNotification = false;
        receiveUpdateOrderAndPayment = true;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(NotificationSettingContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onNotificationCLick() {
        mShowNotificationSetting = !mShowNotificationSetting;
        notifyPropertyChanged(BR.showNotificationSetting);

        mIconNotification = (mIconNotification == mIconExpand ? R.drawable.ic_next_right : mIconExpand);
        notifyPropertyChanged(BR.iconNotification);
    }

    @Override
    public void onReceiveEmailCLick() {
        mShowReceiveEmailSetting = !mShowReceiveEmailSetting;
        notifyPropertyChanged(BR.showReceiveEmailSetting);

        mIconNotificationEmail = (mIconNotificationEmail == mIconExpand ? R.drawable.ic_next_right: mIconExpand);
        notifyPropertyChanged(BR.iconNotificationEmail);
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void onTurnOnReceiveEmail() {
        mReceiveEmail = !mReceiveEmail;
        notifyPropertyChanged(BR.receiveEmail);
    }

    @Override
    public void onReceiveNotificationClick() {
        mReceiveNotification = !mReceiveNotification;
        notifyPropertyChanged(BR.receiveNotification);
    }

    @Override
    public void onNotificationUpdateProductClick() {
        mNotificationUpdateProduct = !mNotificationUpdateProduct;
        notifyPropertyChanged(BR.notificationUpdateProduct);
    }

    @Override
    public void onReceiveChatNotifyClick() {
        notificationChat =!notificationChat;
        notifyPropertyChanged(BR.notificationChat);
    }

    @Override
    public void onNotificationProductPromotionClick() {
        notificationPromotion = !notificationPromotion;
        notifyPropertyChanged(BR.notificationPromotion);
    }

    @Override
    public void onNotificationFollowCLick() {
        notificationFollow = !notificationFollow;
        notifyPropertyChanged(BR.notificationFollow);
    }

    @Override
    public void onNotifyEmptyWarehouseClick() {
        notifyEmptyWarehouse = !notifyEmptyWarehouse;
        notifyPropertyChanged(BR.notifyEmptyWarehouse);
    }

    @Override
    public void onReceiveAppUpdateClick() {
        receiveAppUpdate = !receiveAppUpdate;
        notifyPropertyChanged(BR.receiveAppUpdate);
    }

    @Override
    public void onReceiveUpdateOrderAndPaymentClick() {
        receiveUpdateOrderAndPayment = !receiveUpdateOrderAndPayment;
        notifyPropertyChanged(BR.receiveUpdateOrderAndPayment);
    }

    @Override
    public void onReceiveInfoStoreAndCompany() {
        receiveInfoStoreAndCompany = !receiveInfoStoreAndCompany;
        notifyPropertyChanged(BR.receiveInfoStoreAndCompany);
    }

    @Override
    public void onReceiveNewsClick() {
        receiveNews = !receiveNews;
        notifyPropertyChanged(BR.receiveNews);
    }

    @Override
    public void onReceivePersonalInfoClick() {
        receivePersonalInfo = !receivePersonalInfo;
        notifyPropertyChanged(BR.receivePersonalInfo);
    }

    @Bindable
    public int getIconNotificationEmail() {
        return mIconNotificationEmail;
    }

    @Bindable
    public int getIconNotification() {
        return mIconNotification;
    }

    @Bindable
    public boolean isShowNotificationSetting() {
        return mShowNotificationSetting;
    }

    @Bindable
    public boolean isShowReceiveEmailSetting() {
        return mShowReceiveEmailSetting;
    }

    @Bindable
    public boolean isReceiveEmail() {
        return mReceiveEmail;
    }

    @Bindable
    public boolean isReceiveNotification() {
        return mReceiveNotification;
    }

    @Bindable
    public boolean isNotificationUpdateProduct() {
        return mNotificationUpdateProduct;
    }

    @Bindable
    public boolean isNotificationChat() {
        return notificationChat;
    }

    @Bindable
    public boolean isNotificationPromotion() {
        return notificationPromotion;
    }

    @Bindable
    public boolean isNotificationFollow() {
        return notificationFollow;
    }

    @Bindable
    public boolean isNotifyEmptyWarehouse() {
        return notifyEmptyWarehouse;
    }

    @Bindable
    public boolean isReceiveAppUpdate() {
        return receiveAppUpdate;
    }

    @Bindable
    public boolean isReceiveUpdateOrderAndPayment() {
        return receiveUpdateOrderAndPayment;
    }

    @Bindable
    public boolean isReceiveInfoStoreAndCompany() {
        return receiveInfoStoreAndCompany;
    }

    @Bindable
    public boolean isReceiveNews() {
        return receiveNews;
    }

    @Bindable
    public boolean isReceivePersonalInfo() {
        return receivePersonalInfo;
    }
}
