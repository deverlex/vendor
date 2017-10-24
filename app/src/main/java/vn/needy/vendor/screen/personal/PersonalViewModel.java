package vn.needy.vendor.screen.personal;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.utils.navigator.Navigator;

/**
 * Created by lion on 21/10/2017.
 */

public class PersonalViewModel extends BaseObservable implements PersonalConstract.ViewModel {

    private final Navigator mNavigator;

    private String mAvatarUrl;
    private String mCoverPictureUrl;
    private String mNameUser;

    private boolean mVisibleViewCompany;
    private boolean mVisibleViewStore;
    private boolean mVisibleViewBudget;

    public PersonalViewModel(Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(PersonalConstract.Presenter presenter) {

    }

    @Override
    public void onClickViewAccount() {

    }

    @Override
    public void onClickViewOrderHistory() {

    }

    @Override
    public void onClickViewBudget() {

    }

    @Override
    public void onClickViewCompany() {

    }

    @Override
    public void onClickViewStore() {

    }

    @Override
    public void onClickViewSupport() {

    }

    @Bindable
    public boolean isVisibleViewCompany() {
        return mVisibleViewCompany;
    }

    @Bindable
    public boolean isVisibleViewStore() {
        return mVisibleViewStore;
    }

    @Bindable
    public boolean isVisibleViewBudget() {
        return mVisibleViewBudget;
    }

    @Bindable
    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    @Bindable
    public String getCoverPictureUrl() {
        return mCoverPictureUrl;
    }

    @Bindable
    public String getNameUser() {
        return mNameUser;
    }
}
