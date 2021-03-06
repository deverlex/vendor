package vn.needy.vendor.screen.personal;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 21/10/2017.
 */

public class PersonalConstract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void onClickHeader();

        void onClickViewAccount();

        void onClickViewOrderHistory();

        void onClickViewSupport();

        void onClickViewBusinessManage();

        void onClickSaleIndex();

        void onClickViewBudget();

        void onClickReview();
    }

    interface Presenter extends BasePresenter {

    }
}
