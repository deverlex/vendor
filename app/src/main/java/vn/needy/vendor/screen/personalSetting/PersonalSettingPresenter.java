package vn.needy.vendor.screen.personalSetting;

import android.content.Context;

/**
 * Created by lion on 25/12/2017.
 */

public class PersonalSettingPresenter implements PersonalSettingContract.Presenter{
    private Context mContext;
    private PersonalSettingContract.ViewModel mViewModel;

    public PersonalSettingPresenter(Context mContext) {
        this.mContext = mContext;
        this.mViewModel = mViewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
