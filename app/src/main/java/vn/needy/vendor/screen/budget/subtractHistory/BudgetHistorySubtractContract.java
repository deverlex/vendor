package vn.needy.vendor.screen.budget.subtractHistory;

import java.util.List;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;
import vn.needy.vendor.screen.budget.BudgetActivity;

/**
 * Created by lion on 02/01/2018.
 */

interface BudgetHistorySubtractContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void getListCoin(List<BudgetActivity.Coin> coins);

        void onClickPersonalEarnsCoins();
    }

    interface Presenter extends BasePresenter {

        void setListCoin();
    }
}
