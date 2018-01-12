package vn.needy.vendor.screen.saleIndex;

import vn.needy.vendor.screen.BasePresenter;
import vn.needy.vendor.screen.BaseViewModel;

/**
 * Created by lion on 11/01/2018.
 */

public interface SaleIndexContract {

    interface ViewModel extends BaseViewModel<Presenter>{

        void onBackPressed();

        void onClickViolateStatistic();
    }

    interface Presenter extends BasePresenter{

    }
}
