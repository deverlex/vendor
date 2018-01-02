package vn.needy.vendor.screen.account;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.net.Uri;

import io.realm.Realm;
import vn.needy.vendor.database.sharedprf.SharedPrefsApi;
import vn.needy.vendor.screen.blacklistSetting.BlackListFragment;
import vn.needy.vendor.screen.languageSetting.LanguageSettingFragment;
import vn.needy.vendor.screen.notificationSetting.NotificationSettingFragment;
import vn.needy.vendor.screen.personalSetting.PersonalSettingFragment;
import vn.needy.vendor.screen.userProfile.UserProfileActivity;
import vn.needy.vendor.utils.dialog.DialogManager;
import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 05/12/2017.
 */

public class AccountViewModel extends BaseObservable implements AccountContract.ViewModel {

    private AccountContract.Presenter mPresenter;
    private Navigator mNavigator;
    private Context mContext;
    private DialogManager mDialogManager;
    private Application mApplication;
    private SharedPrefsApi mPrefsApi;
    private Realm mRealm;

    public AccountViewModel(Navigator mNavigator, Context mContext, DialogManager mDialogManager, Application mApplication, SharedPrefsApi mPrefsApi, Realm mRealm) {

        this.mNavigator = mNavigator;
        this.mContext = mContext;
        this.mDialogManager = mDialogManager;
        this.mApplication = mApplication;
        this.mPrefsApi = mPrefsApi;
        this.mRealm = mRealm;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(AccountContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onReviewApplicationClicked() {
        //
        String packageName = mContext.getPackageName();
        String ulr1 = "market://details?id=" + packageName;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(ulr1));
        mNavigator.startActivity(intent);

    }

    @Override
    public void onClearCacheClicked() {
        // delete all Realm and sharedPref
        mPrefsApi.clear();
        // Error transaction, need execute in an transaction
        //mRealm.deleteAll();
    }

    @Override
    public void onUserProfileClicked() {
        mNavigator.startActivity(UserProfileActivity.class);
    }

    @Override
    public void onDeleteAccountClicked() {
        // dialog delete Account
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        String mDeleteAccount = "Do you want delete your account ?";
        //builder.setMessage(android.R.string.deleteAccount);
        builder.setMessage(mDeleteAccount);

        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        ((Activity) mContext).onBackPressed();

    }

    @Override
    public void onPersonalSettingClicked(){
        ((AccountActivity) mContext).initFragment(android.R.id.content , PersonalSettingFragment.getInstance());
    }

    @Override
    public void onNotificationSettingClicked() {
        ((AccountActivity) mContext).initFragment(android.R.id.content , NotificationSettingFragment.getInstance());
    }

    @Override
    public void onBlacklistSettingClicked() {
        ((AccountActivity) mContext).initFragment(android.R.id.content , BlackListFragment.getInstance());
    }

    @Override
    public void onLanguageSettingClicked() {
        ((AccountActivity) mContext).initFragment(android.R.id.content , LanguageSettingFragment.getInstance());
    }

    @Override
    public void onSupportClicked() {

    }

    @Override
    public void onTipAndTrickClicked() {

    }

    @Override
    public void onCommunityRulesClicked() {

    }

    @Override
    public void onNeedRulesClicked() {

    }

    @Override
    public void onLogout() {

    }

}

