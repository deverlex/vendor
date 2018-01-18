package vn.needy.vendor.screen.account;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 05/12/2017.
 */

interface AccountContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onUserProfileClicked();

        void onDeleteAccountClicked();

        void onClearCacheClicked();

        void onReviewApplicationClicked();

        void onBackPressed();

        void onPersonalSettingClicked();

        void onNotificationSettingClicked();

        void onBlacklistSettingClicked();

        void onLanguageSettingClicked();

        void onSupportClicked();

        void onTipAndTrickClicked();

        void onCommunityRulesClicked();

        void onNeedRulesClicked();

        void onLogout();

        void goToLogin();

        void onLogoutError(int msg);
    }

    interface Presenter extends BasePresenter {
        void logout();
    }
}
