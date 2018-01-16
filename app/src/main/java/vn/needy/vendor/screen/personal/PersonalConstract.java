package vn.needy.vendor.screen.personal;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 21/10/2017.
 */

public class PersonalConstract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onClickViewAccount();

        void onClickViewOrderHistory();

        void onClickViewBudget();

        void onClickViewCompany();

        void onClickViewStore();

        void onClickViewSupport();

        void onClickHeader();

        void onClickBudgetActivity();

        void onReviewActivityClick();
    }

    interface Presenter extends BasePresenter {

    }
}
