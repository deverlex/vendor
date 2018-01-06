package vn.needy.vendor.screen.budget.historyRecharge;

import java.util.List;

import vn.needy.vendor.domain.Budget;
import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;
import vn.needy.vendor.screen.budget.BudgetActivity;

/**
 * Created by lion on 02/01/2018.
 */

interface BudgetHistoryRechargeContract {

    interface ViewModel extends BaseViewModel<Presenter> {

        void getCoinList(List<BudgetActivity.Coin> coins);

        void onPersonalEarnsCoinClick();

    }

    interface Presenter extends BasePresenter{

        void getListProduct();

    }
}
