package vn.needy.vendor.screen.notificationSetting;

import android.content.Context;

/**
 * Created by lion on 25/12/2017.
 */

public class NotificationSettingPresenter implements NotificationSettingContract.Presenter{
    private NotificationSettingContract.ViewModel mViewModel;

    private Context mContext;

    public NotificationSettingPresenter(NotificationSettingContract.ViewModel viewModel, Context context) {
        this.mViewModel = viewModel;
        this.mContext = context;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
