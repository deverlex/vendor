package vn.needy.vendor.screen.blacklistSetting;

import java.util.List;

import vn.needy.vendor.model.BlockUser;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 25/12/2017.
 */

interface BlacklistSettingContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onBackPressed();

        void setListBlockUser(List<BlockUser> listBlockUser);

    }

    interface Presenter extends BasePresenter {

        void getUserList();

    }

}
