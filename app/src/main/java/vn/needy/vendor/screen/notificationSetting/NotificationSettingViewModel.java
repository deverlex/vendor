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
    private boolean mShowNotificationEmailSetting;
    private int mIconNotificationEmail ;
    private int mIconNotification ;
    private int mIconExpand = R.drawable.ic_expand;
    private boolean mNotificationEmail;
    private boolean mNotification;
    private boolean notificationGroup;
    private boolean mNotificationUpdateProduct;
    private boolean notificationChat;
    private boolean notificationPromotion;
    private boolean notificationFollow;
    private boolean notificationProducEnd;
    private boolean notificationUpdateApp;
    private boolean notificationProduct;
    private boolean notificationApp;
    private boolean notificationNew;
    private boolean notificationPersonal;

    public NotificationSettingViewModel(Context mContext) {
        this.mContext = mContext;
        mIconNotificationEmail = R.drawable.ic_next_right;
        mIconNotification = R.drawable.ic_next_right;
        mNotificationEmail = false;
        mNotification = false;
        notificationProduct = true;
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
    public void onNotificationEmailCLick() {
        mShowNotificationEmailSetting = !mShowNotificationEmailSetting;
        notifyPropertyChanged(BR.showNotificationEmailSetting);

        mIconNotificationEmail = (mIconNotificationEmail == mIconExpand ? R.drawable.ic_next_right: mIconExpand);

        notifyPropertyChanged(BR.iconNotificationEmail);
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void onNotification() {

    }


    @Override
    public void onNotificationSettingEmail() {
        mNotificationEmail = !mNotificationEmail;
        notifyPropertyChanged(BR.notificationEmail);
    }

    @Override
    public void onNotificationSetting() {
        mNotification = !mNotification;
        notifyPropertyChanged(BR.notification);
    }

    @Override
    public void onNotificationUpdateProductClick() {
        mNotificationUpdateProduct = !mNotificationUpdateProduct;
        notifyPropertyChanged(BR.notificationUpdateProduct);
    }

    @Override
    public void onNotificationChatClick() {
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
    public void onNotificationProductEndCLick() {
        notificationProducEnd = !notificationProducEnd;
        notifyPropertyChanged(BR.notificationProducEnd);
    }

    @Override
    public void onNotificationUpdateAppClick() {
        notificationUpdateApp = !notificationUpdateApp;
        notifyPropertyChanged(BR.notificationUpdateApp);
    }

    @Override
    public void onNotificationProductClick() {
        notificationProduct = !notificationProduct;
        notifyPropertyChanged(BR.notificationProduct);
    }

    @Override
    public void notificationEmailApp() {
        notificationApp = !notificationApp;
        notifyPropertyChanged(BR.notificationApp);
    }

    @Override
    public void notificationNewClick() {
        notificationNew = !notificationNew;
        notifyPropertyChanged(BR.notificationNew);
    }

    @Override
    public void onNotificationPersonalClick() {
        notificationPersonal = !notificationPersonal;
        notifyPropertyChanged(BR.notificationPersonal);
    }

    @Override
    public void onNotificationGroupClick() {
        notificationGroup = !notificationGroup;
        notifyPropertyChanged(BR.notificationGroup);
    }

    @Override
    public void onNotificationSave() {

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
    public boolean getShowNotificationSetting() {
        return mShowNotificationSetting;
    }

    @Bindable
    public boolean getShowNotificationEmailSetting() {
        return mShowNotificationEmailSetting;
    }

    @Bindable
    public boolean isNotificationEmail() {
        return mNotificationEmail;
    }

    @Bindable
    public boolean isNotification() {
        return mNotification;
    }

    @Bindable
    public boolean isNotificationGroup() {
        return notificationGroup;
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
    public boolean isNotificationProducEnd() {
        return notificationProducEnd;
    }

    @Bindable
    public boolean isNotificationUpdateApp() {
        return notificationUpdateApp;
    }

    @Bindable
    public boolean isNotificationProduct() {
        return notificationProduct;
    }

    @Bindable
    public boolean isNotificationApp() {
        return notificationApp;
    }

    @Bindable
    public boolean isNotificationNew() {
        return notificationNew;
    }

    @Bindable
    public boolean isNotificationPersonal() {
        return notificationPersonal;
    }
}
