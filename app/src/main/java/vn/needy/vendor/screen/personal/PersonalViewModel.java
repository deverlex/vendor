package vn.needy.vendor.screen.personal;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.needy.vendor.screen.account.AccountActivity;
import vn.needy.vendor.screen.budget.BudgetActivity;
import vn.needy.vendor.screen.companyProfile.CompanyProfileActivity;
import vn.needy.vendor.screen.review.ReviewActivity;
import vn.needy.vendor.screen.storeProfile.StoreProfileActivity;
import vn.needy.vendor.screen.userProfile.UserProfileActivity;
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
        mNavigator.startActivity(AccountActivity.class);
    }

    @Override
    public void onClickViewOrderHistory() {

    }

    @Override
    public void onClickViewBudget() {

    }

    @Override
    public void onClickViewCompany() {
        mNavigator.startActivity(CompanyProfileActivity.class);
    }

    @Override
    public void onClickViewStore() {
        mNavigator.startActivity(StoreProfileActivity.class);
    }

    @Override
    public void onClickViewSupport() {

    }

    @Override
    public void onClickHeader() {
        mNavigator.startActivity(UserProfileActivity.class);
    }

    @Override
    public void onClickBudgetActivity() {
        mNavigator.startActivity(BudgetActivity.class);
    }

    @Override
    public void onReviewActivityClick() {
        mNavigator.startActivity(ReviewActivity.class);
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
