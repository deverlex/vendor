package vn.needy.vendor.screen.account;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 05/12/2017.
 */

class AccountContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onDeleteAccountClicked();

        void onDeleteCacheClicked();

        void onEvaluationClicked();

        void onBackPressed();

        void onPersonalInfoSettingClicked();

        void onNotificationAccount();

        void onAccountBlock();

        void onAcountLanguage();

        void onAcountSupport();

        void onAcountTipAndTrick();

        void onAcountCommunity();

        void onNeedRules();

        void onAcountLogout();

    }

    interface Presenter extends BasePresenter {

    }
}
